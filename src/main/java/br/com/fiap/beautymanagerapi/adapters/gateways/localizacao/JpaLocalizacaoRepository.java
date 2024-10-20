package br.com.fiap.beautymanagerapi.adapters.gateways.localizacao;

import br.com.fiap.beautymanagerapi.entities.LocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaLocalizacaoRepository extends JpaRepository <LocalizacaoEntity,Long>{

    // Query utilizando a fórmula de Haversine no H2
    @Query("SELECT l FROM LocalizacaoEntity l WHERE " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * " +
            "cos(radians(l.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(l.latitude)))) < :radius")
    List<LocalizacaoEntity> findLocationsWithinRadius(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius);

}
