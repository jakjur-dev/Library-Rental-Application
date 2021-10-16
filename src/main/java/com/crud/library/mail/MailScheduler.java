package com.crud.library.mail;

import com.crud.library.domain.Reader;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.service.ReaderService;
import com.crud.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailScheduler {

    private static final String SUBJECT = "IT Library: Reminder";
    private final MailService mailService;
    private final RentalService rentalService;
    private final ReaderService readerService;

    @Scheduled(cron = "0 0 1 * * *")
    public void sendMailToAllReaders() {
        readerService.getAllReaders().forEach(reader -> {
            try {
                sendInformationEmail(reader);
            } catch (ReaderNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendInformationEmail(Reader reader) throws ReaderNotFoundException {
        long size = rentalService.findAllDueRentalsOfReader(reader.getId()).size();

        if (size > 1) {
            mailService.send(
                    new Mail(
                            reader.getEmail(),
                            SUBJECT,
                            "You have" + size + "unreturned books",
                            null
                    ), reader
            );
        } else if (size == 1) {
            mailService.send(
                    new Mail(
                            reader.getEmail(),
                            SUBJECT,
                            "You have an unreturned book",
                            null
                    ), reader
            );
        }
    }

}
