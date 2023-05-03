package com.abm.persona.api.repository;

import com.abm.persona.api.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query("SELECT p FROM Persona p WHERE (:nombre is null or p.nombre like %:nombre%) and (:idTipoDocumento is null or p.tipoDocumento.id = :idTipoDocumento)")
    Page<Persona> search(@Param("nombre") String nombre, @Param("idTipoDocumento") Integer  idTipoDocumento, Pageable pageable);

    Page<Persona> findByNombreContainingAndTipoDocumento_Id(@Nullable String nombre, @Nullable Integer idTipoDocumento, Pageable pageable);


}
