package com.example.back.services;

import com.example.back.entity.Product;
import com.example.back.entity.User;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Value("${upload.directory}") // Ruta definida en application.properties
    private String uploadDir;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;  // Añadir el repositorio de User

    // Crear un nuevo producto
    public Product createProduct(Product product) {
        // Verificar si el usuario asociado al producto ya existe
        User user = product.getUser();
        if (user != null && user.getIduser() != null) {
            Optional<User> existingUser = userRepository.findById(user.getIduser());
            if (!existingUser.isPresent()) {
                // Si el usuario no existe, lanzamos una excepción o lo guardamos
                throw new IllegalArgumentException("User not found");
            }
        }

        // Si el usuario ya existe, lo asignamos al producto
        if (user != null) {
            product.setUser(user);
        }

        // Guardamos el producto
        return productRepository.save(product);
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Actualizar un producto existente
    public Product updateProduct(Integer id, Product productDetails) {
        if (productRepository.existsById(id)) {
            productDetails.setIdproduct(id);
            return productRepository.save(productDetails);
        }
        return null; // O lanzar una excepción si lo prefieres
    }

    // Eliminar un producto por su ID
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Product uploadImage(MultipartFile file, int idproduct){
        try {
            // Crear el directorio si no existe
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Guardar el archivo
            String fileName =  "doument"+idproduct+".jpg";
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            // Generar URL pública
            String fileUrl = fileName;

            Optional<Product> product = productRepository.findById(idproduct);
            if (product.isPresent()) {
                Product product1 = product.get();
                product1.setImageUrl(fileUrl);
                return productRepository.save(product1);
            }else {
                throw new IllegalArgumentException("Product not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Product not found");
        }
    }
}
