package springboot.jewelry.api.security.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import springboot.jewelry.api.security.cache.LoggedOutJwtTokenCache;
import springboot.jewelry.api.security.dto.DeviceInfoDto;
import springboot.jewelry.api.security.event.OnCustomerLogoutSuccessEvent;

@Component
public class OnCustomerLogoutSuccessEventListener implements ApplicationListener<OnCustomerLogoutSuccessEvent> {

    private final LoggedOutJwtTokenCache tokenCache;
    private static final Logger logger = LoggerFactory.getLogger(OnCustomerLogoutSuccessEventListener.class);

    @Autowired
    public OnCustomerLogoutSuccessEventListener(LoggedOutJwtTokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    @Override
    public void onApplicationEvent(OnCustomerLogoutSuccessEvent event) {
        if (event != null) {
            DeviceInfoDto deviceInfoDto = event.getLogoutDto().getDeviceInfo();
            logger.info(String.format("Log out success event received for user [%s] for device [%s]",
                                                            event.getCustomerEmail(), deviceInfoDto));
            tokenCache.markLogoutEventForToken(event);
        }
    }
}
