package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.generated.model.UpdateBookingsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class BookingApiController implements BookingApi {
    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        log.warn("Method 'getBookings' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateBookings(UpdateBookingsRequest updateBookingsRequest) {
        log.warn("Method 'updateBookings' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

