package com.arcaneia.spendwise.data.repository

import com.arcaneia.spendwise.data.dao.CategoriaDao
import com.arcaneia.spendwise.data.entity.Categoria

class CategoriaRepository(private val categoriaDao: CategoriaDao) {

    suspend fun insert(categoria: Categoria) = categoriaDao.insert(categoria)
    suspend fun delete(categoria: Categoria) = categoriaDao.delete(categoria)
}