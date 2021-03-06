package com.jvschool.view;

import com.jvschool.dto.ProductsDTO;
import com.jvschool.util.Receiver;
import lombok.extern.log4j.Log4j;
//import org.omnifaces.cdi.Push;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;

@Log4j
@SessionScoped
@ManagedBean(name = "topListener")
public class MyMessageListener implements MessageListener, Serializable {

    @EJB
    private Receiver receiver;

    public MyMessageListener(Receiver receiver) {
        this.receiver=receiver;
        log.info("Listener receiver created");
    }

    public MyMessageListener() {
        log.info("Listener default created");
    }

    @Override
    public void onMessage(Message message) {

        log.info("Message received");
        receiver.topReview();

    }

    public ProductsDTO getProductsDTO() {
        return receiver.getProductsDTO();
    }

    public void setProductsDTO(ProductsDTO productsDTO) {
        receiver.setProductsDTO(productsDTO);
    }

}
