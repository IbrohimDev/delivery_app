package uz.gita.evos_app.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.evos_app.data.enums.StartScreenEnum
import uz.gita.evos_app.data.shared.MyPrefs
import uz.gita.evos_app.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val prefs:MyPrefs
):AuthRepository {
    override fun userLogin(): Flow<Result<Unit>> = flow{
        prefs.startScreen = StartScreenEnum.MAIN
        emit(Result.success(Unit))
    }

    override fun userRegister(): Flow<Result<Unit>> = flow{
        prefs.startScreen = StartScreenEnum.MAIN
        emit(Result.success(Unit))
    }

}