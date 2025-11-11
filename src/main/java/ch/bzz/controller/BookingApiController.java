package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.generated.model.UpdateBookingsRequest;
import ch.bzz.repository.BookingRepository;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class BookingApiController implements BookingApi {
    private final HttpServletRequest request;
    private final BookingRepository bookingRepository;
    private final ProjectRepository projectRepository;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public BookingApiController(HttpServletRequest request, BookingRepository bookingRepository, ProjectRepository projectRepository, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.request = request;
        this.bookingRepository = bookingRepository;
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        try {
            String header = request.getHeader("Authorization");
            String token = header.substring(7);

            String projectName = jwtUtil.getClaim(token, Claims::getSubject);
            Optional<ch.bzz.model.Project> projectOpt = projectRepository.findByProjectName(projectName);
            if (projectOpt.isPresent()) {
                List<ch.bzz.model.Booking> bookings = bookingRepository.findByProject(projectOpt.get());
                List<Booking> bookingDtos = bookings.stream()
                        .map(booking -> modelMapper.map(booking, Booking.class))
                        .toList();
                return ResponseEntity.ok(bookingDtos);
            } else {
                log.warn("Project not found for token subject: {}", projectName);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error retrieving bookings", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Void> updateBookings(UpdateBookingsRequest updateBookingsRequest) {
        log.warn("Method 'updateBookings' not implemented yet.");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

