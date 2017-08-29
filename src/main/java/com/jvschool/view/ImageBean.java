package com.jvschool.view;

import com.jvschool.dto.ProductDTO;
import com.jvschool.util.Receiver;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ImageBean {

    @EJB
    private Receiver receiver;


    public byte[] getImage(final Long productId) {

        if (productId!=null && !receiver.getProductsDTO().getProducts().isEmpty()) {

            ProductDTO productDTO = receiver.getProductsDTO().getProducts().stream()
                    .filter(x -> productId.equals(x.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (productDTO != null) {
                return productDTO.getPicture();
            } else return null;
        }
        else return null;
    }

}
