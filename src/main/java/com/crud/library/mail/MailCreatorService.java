package com.crud.library.mail;

import com.crud.library.domain.Reader;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailCreatorService {

    private final MailConfig mailConfig;
    private final RentalService rentalService;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildRemainderMail(String message, Reader reader) throws ReaderNotFoundException {

        List<String> books = rentalService.findAllActiveRentalsOfReader(reader.getId()).stream().map(rental -> rental.getBook().getTitle().getTitle()).collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Create new tasks");
        context.setVariable("admin_config", mailConfig);
        context.setVariable("preview_message", "Your daily update");
        context.setVariable("goodbye_message", "Good luck in completing your tasks!");
        context.setVariable("is_friend", false);
        context.setVariable("book_list", books);
        context.setVariable("list_empty", books.isEmpty());

        return templateEngine.process("mail/scheduled-mail", context);
    }

}
