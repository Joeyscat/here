package fun.oook.here.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/10/25 23:58
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    final static private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResponse<String> handle(Exception e, HttpServletRequest httpRequest) {
        Set<MediaType> mediaTypeSet = new HashSet<>();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("utf-8"));
        mediaTypeSet.add(mediaType);

        httpRequest.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, mediaTypeSet);

        logger.warn("Returning HTTP 400 Bad Request", e);

        return new RestResponse<>("E000-", e.getMessage());
    }
}
