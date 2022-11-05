package com.mypisubproject.PiSub.Project.paystack;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mypisubproject.PiSub.Project.email.EmailContent;
import com.mypisubproject.PiSub.Project.email.MailService;
import com.mypisubproject.PiSub.Project.enums.TransactionType;
import com.mypisubproject.PiSub.Project.exceptions.BadCallException;
import com.mypisubproject.PiSub.Project.exceptions.ResourceNotFound;
import com.mypisubproject.PiSub.Project.model.Transaction;
import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.model.Wallet;
import com.mypisubproject.PiSub.Project.repository.TransactionRepository;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.repository.WalletRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@Service
public class PaystackService {

    private static final Logger logger = LoggerFactory.getLogger(PaystackService.class);
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final Transaction transaction;
    private final TransactionRepository transactionRepository;
    private final EmailContent emailContent;


    public PaystackService(WalletRepository walletRepository, UserRepository userRepository, Transaction transaction, TransactionRepository transactionRepository, MailService mailService, EmailContent emailContent) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.transaction = transaction;
        this.transactionRepository = transactionRepository;

        this.emailContent = emailContent;
    }

    public PaystackPaymentResponse initTransaction(PaystackPaymentRequest request) throws Exception {


        logger.info("===== initializing paystack transaction =====");
        PaystackPaymentResponse initializeTransactionResponse = null;


        try {

            Gson gson = new Gson();

            BigDecimal refAmount = request.getAmount();
            BigDecimal toNaira = new BigDecimal("100");

            BigDecimal newAmount = refAmount.multiply(toNaira);
            BigDecimal newAmount2 = new BigDecimal("0.02");

            BigDecimal chargePercent = newAmount.multiply(newAmount2);

            BigDecimal a = toNaira.multiply(newAmount2).divide(BigDecimal.valueOf(100));
            BigDecimal charge = a.multiply(request.getAmount());
            BigDecimal calculatedAmount = newAmount.add(chargePercent);

            request.setAmount(calculatedAmount);

            logger.info("===== finished setting the charge on the transaction amount ===== NGN{} ", charge);

            StringEntity postingString = new StringEntity(gson.toJson(request));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://api.paystack.co/transaction/initialize");

            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer sk_test_5ff54b09099f5c2c71e9d895f385b010b0c63617");
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);

            logger.info("===== executed the post request for transaction initialization =====");
            logger.error("=== please confirm the transaction status =====");
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("The status code is " + statusCode);


            if (response.getStatusLine().getStatusCode() == 200) {
                logger.info(">>>>>>>>>> this is a successful transaction <<<<<<<<<<");
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                    System.out.println("I am printing this line " + line);
                }

            } else {
                throw new BadCallException("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();
            initializeTransactionResponse = mapper.readValue(result.toString(), PaystackPaymentResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializing paystack transaction");
        }
        try {
            transaction.setAmount(request.getAmount().divide(BigDecimal.valueOf(100)));
            transaction.setFees(request.getAmount().divide(BigDecimal.valueOf(10000)));
            transaction.setTransTime(transaction.getTransTime());
            transaction.setTransRef("PS"+initializeTransactionResponse.getData().getReference());
            transaction.setCurrency("NGN");
            transaction.setStatus("PENDING");
            transaction.setTranType(TransactionType.CARD);
            transaction.setIp_address(" . . .");
            transaction.setPaid_at(" . . .");
            Optional<User> foundByEmail = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));
            transaction.setUser(foundByEmail.get());

            Transaction saved = transactionRepository.save(transaction);
            logger.info("==== saved the transaction objects to transaction table ====");
            logger.info("==== this is your transaction reference ===== {}", transaction.getTransRef());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return initializeTransactionResponse;
    }

    public VerifyTransactionResponse verifyTransaction(String reference) throws Exception {
        logger.info("===== inside transaction verification function =====");
        VerifyTransactionResponse paystackresponse = null;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + reference);

            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer sk_test_5ff54b09099f5c2c71e9d895f385b010b0c63617");
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error occurred while connecting to paystack url");
            }
            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            paystackresponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);

            if (paystackresponse == null || !paystackresponse.isStatus()) {
                throw new Exception("An error occurred while  verifying payment");
            } else if (paystackresponse.getData().getStatus().equals("success")) {

                logger.error(">>>>>>>>>> the transaction was verified and successful <<<<<<<<<<");
                logger.info("...................................................................");
                logger.info("...................................................................");
                logger.info("...................................................................");
                logger.info("...........about to apply value and save to the database...........");

                //PAYMENT IS SUCCESSFUL, APPLY VALUE TO THE TRANSACTION

                Optional<Transaction> byTransRef = transactionRepository.findByTransRef("PS" + paystackresponse.getData().getReference());

                    transaction.setIp_address(paystackresponse.getData().getIp_address());
                    transaction.setPaid_at(paystackresponse.getData().getPaid_at());
                    transaction.setStatus("SUCCESSFUL");
                    transaction.setTransTime(paystackresponse.getData().getTransaction_date());
                    transaction.setUser(transaction.getUser());
//                    Transaction save = transactionRepository.save(transaction);
                    logger.info("saved successful transactions, ref = ......... {} ", transaction.getTransRef());


                Optional<Wallet> wallet = walletRepository.findById(transaction.getUser().getWallet().getId());
                if (wallet.isPresent()){
                    wallet.get().setBalance(wallet.get().getBalance().add(transaction.getAmount()));
                    walletRepository.save(wallet.get());
                }else
                    throw new ResourceNotFound("wallet", "id", wallet.get().getId());

                transactionRepository.save(transaction);
                try {
                    String title = "You funded your akpa ego wallet";

                    String msg = "Dear "+transaction.getUser().getFirstName()+
                            ", Thank you. You successfully funded your wallet. You spent ₦"+transaction.getAmount()+ " in total this time.";
                    emailContent.setBody(msg);

                    mailService.sendAccountFundingMail(transaction.getUser().getEmail(),
                            msg,
                            title);
                } catch (MailjetException | MailjetSocketTimeoutException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Internal server error");
        }
        return paystackresponse;
    }

    public VerifyTransactionResponse deposit(Principal principal, PaystackPaymentRequest request) throws Exception {
        PaystackPaymentResponse initializeTransactionResponse = initTransaction( request);
        VerifyTransactionResponse paystackresponse = verifyTransaction(initializeTransactionResponse.getData().getReference());

        return paystackresponse;
    }
}
