/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import javax.persistence.Table;
import org.foi.air.optimix.model.RecipeRaws;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
@Table(name = "recipe_raws")
public interface RecipeRawsRepository extends JpaRepository<RecipeRaws, String> {

    public RecipeRaws findByIdRecipeRaws(long id);
}
