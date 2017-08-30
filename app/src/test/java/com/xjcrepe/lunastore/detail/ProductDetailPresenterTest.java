package com.xjcrepe.lunastore.detail;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.repo.ProductsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xijunli on 29/8/17.
 */
public class ProductDetailPresenterTest {

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private ProductDetailContract.View view;

    private ProductDetailPresenter subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new ProductDetailPresenter(productsRepository);
        subject.bindView(view);
    }

    @Test
    public void loadProductById_onSuccess_showsProduct() {
        String fakeId = "fake-id";
        Product mockProduct = mock(Product.class);
        Single<Product> productSingle = Single.just(mockProduct);
        when(productsRepository.getProductById(fakeId)).thenReturn(productSingle);

        subject.loadProductById(fakeId);

        verify(view).showProduct(mockProduct);
    }

    @Test
    public void addProductToCart_onSuccess_showsSuccessMessage() {
        String fakeId = "fake-id";
        when(productsRepository.addToCart(fakeId)).thenReturn(Single.just(Boolean.TRUE));

        subject.addProductToCart(fakeId);

        verify(view).showAddCartSuccessMessage();
    }

    @Test
    public void addProductToCart_onFailure_showsFailedMessage() {
        String fakeId = "fake-id";
        when(productsRepository.addToCart(fakeId)).thenReturn(Single.just(Boolean.FALSE));

        subject.addProductToCart(fakeId);

        verify(view).showAddCartFailedMessage();
    }

    @Test
    public void addProductToCart_onError_showsErrorMessage() {
        String fakeId = "fake-id";
        Throwable throwable = new Throwable("error");
        when(productsRepository.addToCart(fakeId)).thenReturn(Single.<Boolean>error(throwable));

        subject.addProductToCart(fakeId);

        verify(view).showMessage("error");
    }
}