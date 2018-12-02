package fun.oook.here.web.rest;

import fun.oook.here.common.CommonException;
import fun.oook.here.web.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
abstract class AbstractRestController {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    interface ExceptionHandler<T> {
        RestResponse<T> handle();
    }

    public <T> RestResponse<T> exceptionHandler(ExceptionHandler<T> exceptionHandler) {

        RestResponse<T> restResponse;

        try {

            restResponse = exceptionHandler.handle();

        } catch (CommonException e) {
            logger.error(e.getMessage(), e);
            restResponse = new RestResponse<>(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            restResponse = new RestResponse<T>().excetpionRestResponse();
        }

        return restResponse;
    }
}
