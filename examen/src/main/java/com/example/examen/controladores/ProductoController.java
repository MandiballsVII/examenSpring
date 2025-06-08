package com.example.examen.controladores;

import com.example.examen.entidades.Producto;
import com.example.examen.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
    	productoRepository.save(producto);
        return "redirect:/productos";
    }
    @GetMapping
    public String listarPrueba(Model model) {
        var lista = productoRepository.findAll();
        lista.forEach(System.out::println);
        model.addAttribute("productos", lista);
        return "listar";
    }
    @GetMapping("/{id}")
    public String verProductoPorId(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        model.addAttribute("producto", producto);
        return "detalle"; // Vista para mostrar un producto
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) {
            return "redirect:/productos"; // o muestra un error
        }
        model.addAttribute("producto", producto);
        return "formulario"; // reutilizamos el mismo HTML del alta
    }

}

