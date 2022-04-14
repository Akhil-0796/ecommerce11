package com.example.ecommerce.service.ServiceImpl;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        if (productDTO.getProductId().isEmpty())
        productDTO.setProductId(UUID.randomUUID().toString());
        productRepository.save(dtoMapper.productDtoToProduct(productDTO));
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product:productRepository.findAll()) productDTOList.add(dtoMapper.productToProductDto(product));
        return productDTOList;
    }

    @Override
    public ProductDTO findById(String productId) {
        Optional<Product> productDb = productRepository.findById(productId);
        if(!productDb.isPresent()) return null;
        return dtoMapper.productToProductDto(productDb.get());
    }

    @Override
    public List<Product> findAllProduct(List<String> items) {
        ArrayList<Product> itemList = new ArrayList<>();

        for(String item:items) itemList.add(productRepository.findById(item).get());
        return itemList;
    }
}
