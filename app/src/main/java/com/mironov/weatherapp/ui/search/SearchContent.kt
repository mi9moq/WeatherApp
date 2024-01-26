package com.mironov.weatherapp.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mironov.weatherapp.R
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.search.SearchComponent
import com.mironov.weatherapp.presenation.search.SearchStore
import com.mironov.weatherapp.ui.extesions.ApplyLoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(component: SearchComponent) {

    val state by component.model.collectAsState()
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    SearchBar(
        modifier = Modifier.focusRequester(focusRequester),
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        query = state.searchQuery,
        onQueryChange = { component.changeQuery(it) },
        onSearch = { component.onClickSearch() },
        active = true,
        onActiveChange = {},
        leadingIcon = {
            IconButton(onClick = { component.onClickBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { component.onClickSearch() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    ) {
        when (val searchState = state.searchState) {
            is SearchStore.State.SearchState.Content -> {
                ApplyContentState(
                    cities = searchState.cities,
                    onClickCity = { component.onClickCity(it) }
                )
            }

            SearchStore.State.SearchState.EmptyResult -> {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.nothing_find)
                )
            }

            SearchStore.State.SearchState.Error -> {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.something_went_wrong)
                )
            }

            SearchStore.State.SearchState.Initial -> Unit
            SearchStore.State.SearchState.Loading -> {
                ApplyLoadingState()
            }
        }
    }
}

@Composable
private fun ApplyContentState(
    cities: List<City>,
    onClickCity: (City) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            items = cities,
            key = { it.id }
        ) { city ->
            CityItem(city = city, onClickCity = { onClickCity(city) })
        }
    }
}

@Composable
private fun CityItem(
    city: City,
    onClickCity: (City) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickCity(city) }
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = city.name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = city.country,
            )
        }
    }
}