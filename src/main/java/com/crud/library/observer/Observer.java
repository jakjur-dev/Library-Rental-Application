package com.crud.library.observer;

import com.crud.library.domain.Rental;
import com.crud.library.scheduler.mail.Mail;

public interface Observer {
    Mail update(Rental rental);
}