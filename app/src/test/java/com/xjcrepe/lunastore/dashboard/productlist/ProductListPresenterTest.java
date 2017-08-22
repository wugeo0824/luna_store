package com.xjcrepe.lunastore.dashboard.productlist;

import com.xjcrepe.lunastore.repo.ProductsRepository;
import com.xjcrepe.lunastore.repo.SampleData;
import com.xjcrepe.lunastore.model.Product;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by LiXijun on 2017/8/21.
 */
public class ProductListPresenterTest {

    private ProductListPresenter subject;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private ProductListContract.View productsView;

    private List<Product> mockData;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockData = SampleData.getProductsMockData();
        Single<List<Product>> productsTestObservable = Single.just(mockData);
        when(productsRepository.getProductsByCategory(anyInt()))
                .thenReturn(productsTestObservable);
        subject = new ProductListPresenter(productsRepository);
        subject.bindView(productsView);
    }

    @Test
    public void loadProductsByCategory_loadFromRepoWithCorrectCategory() {
        subject.loadProductsByCategory(Product.CATEGORY_FURNITURE);

        verify(productsRepository).getProductsByCategory(Product.CATEGORY_FURNITURE);
    }

    @Test
    public void loadProductsByCategory_onSuccess_showProducts() {
        subject.loadProductsByCategory(Product.CATEGORY_FURNITURE);

        verify(productsView).showProducts(mockData);
    }

    @Test
    public void loadProductsByCategory_onFailure_showErrorMessage() {
        Throwable throwable = new Throwable("error-message");
        Single<List<Product>> error = Single.error(throwable);
        when(productsRepository.getProductsByCategory(Product.CATEGORY_FURNITURE))
                .thenReturn(error);
        subject.loadProductsByCategory(Product.CATEGORY_FURNITURE);

        verify(productsView).showErrorMessage("error-message");
    }

    private List<Product> getMockProducts() {
        return SampleData.getProductsMockData();
    }
}