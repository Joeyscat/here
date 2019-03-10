package fun.oook.here.web.rest;

import fun.oook.here.common.HereException;
import fun.oook.here.entity.RestResponse;
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
        /**
         *
         * @return response
         */
        RestResponse<T> handle();
    }

    public <T> RestResponse<T> exceptionHandler(ExceptionHandler<T> exceptionHandler) {

        RestResponse<T> restResponse;

        try {

            restResponse = exceptionHandler.handle();

        } catch (HereException e) {
            logger.error(e.getMessage(), e);
            restResponse = new RestResponse<>(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            restResponse = new RestResponse<T>().excetpionRestResponse();
        }

        return restResponse;
    }
}
