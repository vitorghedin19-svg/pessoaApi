package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Animais;
import br.com.senac.api.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimaisRepository extends JpaRepository<Animais, Long> {
}
