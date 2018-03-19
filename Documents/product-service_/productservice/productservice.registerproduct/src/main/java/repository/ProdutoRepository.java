package repository;

import org.springframework.data.repository.CrudRepository;

import model.Produto;


public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
