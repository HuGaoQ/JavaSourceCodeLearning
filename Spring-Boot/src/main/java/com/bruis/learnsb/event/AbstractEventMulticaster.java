package com.bruis.learnsb.event;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@Component
public abstract class AbstractEventMulticaster implements EventMulticaster {

    @Resource
    private List<WeatherListener> listenerList;

    @Override
    public void multicastEvent(WeatherEvent event) {
        doStart();
        listenerList.forEach(i -> i.onWeatherEvent(event));
        doEnd();
    }

    @Override
    public void addListener(WeatherListener weatherListener) {
        listenerList.add(weatherListener);
    }

    @Override
    public void removeListener(WeatherListener weatherListener) {
        listenerList.remove(weatherListener);
    }

    abstract void doStart();

    abstract void doEnd();

}
