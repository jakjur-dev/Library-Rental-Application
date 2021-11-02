package com.crud.library.mail;

import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import com.crud.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailScheduler {
    private static final String SUBJECT = "LibraryManager: Information about return date of your rents";

    private final MailService mailService;
    private final ReaderRepository readerRepository;
    private final RentalService rentalService;

    @Scheduled(cron = "0 0 23 * * 7")
    public void sendInformationAboutRentedBook() throws ReaderNotFoundException {

        for(Reader reader : readerRepository.findAll()) {
            if (reader.getRentalsList().size() > 0) {
                mailService.send(new Mail(
                        reader.getEmail(),
                        SUBJECT,
                        getUserRents(reader)
                ));
            }
        }

    }

    private String getUserRents(Reader reader) throws ReaderNotFoundException {

        List<Rental> rentals = rentalService.findAllDueRentalsOfReader(reader.getId());

        StringBuilder rentalsString = new StringBuilder();

        rentalsString.append("You rented ").append(rentals.size()).append(rentals.size() < 2 ? " book" : " books").append(" from the Library \n\n");

        for(Rental rental : rentals) {
            rentalsString
                    .append(rental.getBook().getTitle().getTitle())
                    .append(" - ")
                    .append(rental.getBook().getTitle().getAuthor())
                    .append(" - rent date: ")
                    .append(rental.getRentDate())
                    .append(" - return date: ")
                    .append(rental.getReturnDate());
        }

        return rentalsString.toString();
    }
}
