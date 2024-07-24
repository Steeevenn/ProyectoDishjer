package com.app.clienteapp.repository;

import com.app.clienteapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Indicamos que es de tipo respository, y extendemos de la clase JpaRepository para que sea correcto
// nos genera los metodos que posteriormente vamos a utilizar en la clase de servicio

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
     /*   @Override
        List<Cliente> findAll();
*/


}
