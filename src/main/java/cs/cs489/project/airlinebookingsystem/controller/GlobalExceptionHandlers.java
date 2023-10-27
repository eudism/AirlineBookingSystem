//package cs.cs489.project.airlinebookingsystem.controller;
//
//
//import cs.cs489.project.airlinebookingsystem.dto.GlobalErrorResponse;
//import cs.cs489.project.airlinebookingsystem.exception.RecordAlreadyPresentException;
//import cs.cs489.project.airlinebookingsystem.exception.RecordNotFoundException;
//import cs.cs489.project.airlinebookingsystem.exception.ScheduledFlightAlreadyBookedException;
//import cs.cs489.project.airlinebookingsystem.exception.ScheduledFlightNotFoundException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {
//
//
//    @ExceptionHandler(value = {
//            ScheduledFlightAlreadyBookedException.class,
//            ScheduledFlightNotFoundException.class,
//            RecordAlreadyPresentException.class,
//            RecordNotFoundException.class
//    })
//    public ResponseEntity<?> handleScheduleFlightModificationError(final RuntimeException ex, final WebRequest request) {
//        return handleExceptionInternal(ex,
//                new GlobalErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
//                new HttpHeaders(),
//                HttpStatus.BAD_REQUEST,
//                request);
//    }
//}
