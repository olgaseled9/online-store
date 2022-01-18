package by.itacademy.javaenterprise.seledtsova.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceExceptionTest {

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(ServiceException.class, () ->
        {
            throw new ServiceException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

}