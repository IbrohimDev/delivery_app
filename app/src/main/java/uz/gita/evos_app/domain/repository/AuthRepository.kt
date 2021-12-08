package uz.gita.evos_app.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun userLogin() : Flow<Result<Unit>>

    fun userRegister() : Flow<Result<Unit>>
}