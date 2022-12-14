package com.springbootexpert;

import com.springbootexpert.domain.entity.Cliente;
import com.springbootexpert.domain.entity.Pedido;
import com.springbootexpert.domain.repository.Clientes;
import com.springbootexpert.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringBootExpertApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired Clientes clientes,
			@Autowired Pedidos pedidos
	) {
		return args -> {
			System.out.println("Salvando clientes");
			Cliente fulano = new Cliente("Outro cliente");
			clientes.save(fulano);

			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido((LocalDate.now()));
			p.setTotal(BigDecimal.valueOf(100));

			pedidos.save(p);

			Cliente cliente = clientes.findClienteFetchPedidos((fulano.getId()));
			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}
