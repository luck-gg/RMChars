package com.luckgg.rmchars.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.local.RepoDatabase
import com.luckgg.rmchars.data.remote.CharacterRemoteMediator
import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.data.remote.response.CharacterApiResponse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalPagingApi
class CharacterRemoteMediatorTest {
    private val mockApi = mock(RMApi::class.java)

    private val mockDb = mock(RepoDatabase::class.java)

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `prepend loadType return Success`() =
        runTest {
            val query = "Rick"
            val characterEntity =
                CharacterEntity(
                    id = 1695,
                    name = "Curtis Baird",
                    status = "aliquet",
                    species = "mi",
                    image = "mutat",
                    gender = "noster",
                    created = "conclusionemque",
                    url = "https://duckduckgo.com/?q=maiorum",
                    locationOrigin = "disputationi",
                    locationCurrent = "ubique",
                    type = "AA",
                )
            val remoteMediator =
                CharacterRemoteMediator(
                    mockDb,
                    mockApi,
                    query,
                )
            val pagingState =
                PagingState<Int, CharacterEntity>(
                    listOf(PagingSource.LoadResult.Page(listOf(characterEntity), null, 2)),
                    null,
                    PagingConfig(20),
                    10,
                )
            val response: CharacterApiResponse = mock()

            `when`(mockApi.getCharacters(anyInt(), anyString())).thenReturn(response)

            val result = remoteMediator.load(LoadType.PREPEND, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
        }
}
