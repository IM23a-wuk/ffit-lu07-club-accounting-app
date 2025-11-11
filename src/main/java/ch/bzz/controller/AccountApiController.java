package ch.bzz.controller;

import ch.bzz.generated.api.AccountApi;
import ch.bzz.generated.model.Account;
import ch.bzz.generated.model.UpdateAccountsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AccountApiController implements AccountApi {
    @Override
    public ResponseEntity<List<Account>> getAccounts() {
        log.warn("Method 'getAccounts' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateAccounts(UpdateAccountsRequest updateAccountsRequest) {
        log.warn("Method 'updateAccounts' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
