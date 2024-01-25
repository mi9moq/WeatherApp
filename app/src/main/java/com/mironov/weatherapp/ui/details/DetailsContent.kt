package com.mironov.weatherapp.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mironov.weatherapp.R
import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.ForecastWeather
import com.mironov.weatherapp.domain.entity.Weather
import com.mironov.weatherapp.presenation.details.DetailsComponent
import com.mironov.weatherapp.presenation.details.DetailsStore
import com.mironov.weatherapp.ui.extesions.formattedDate
import com.mironov.weatherapp.ui.extesions.formattedDayOfWeek
import com.mironov.weatherapp.ui.extesions.formattedUpcomingWeather
import com.mironov.weatherapp.ui.extesions.tempToFormattedString
import com.mironov.weatherapp.ui.theme.CardGradients

@Composable
fun DetailsContent(component: DetailsComponent) {

    val state by component.model.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(CardGradients.gradients[1].primaryGradient),
        containerColor = Color.Transparent,
        topBar = {
            TopBar(
                cityName = state.city.name,
                isFavourite = state.isFavourite,
                onClickBack = { component.onClickBack() },
                onClickChangeFavouriteStatus = { component.onClickChangeFavouriteStatus() }
            )

        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            when (val forecastState = state.forecastState) {
                is DetailsStore.State.ForecastState.Content -> {
                    ApplyContentState(forecast = forecastState.forecast)
                }

                DetailsStore.State.ForecastState.Error -> {}

                DetailsStore.State.ForecastState.Initial -> Unit

                DetailsStore.State.ForecastState.Loading -> {
                    ApplyLoadingState()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    cityName: String,
    isFavourite: Boolean,
    onClickBack: () -> Unit,
    onClickChangeFavouriteStatus: () -> Unit,
) {

    CenterAlignedTopAppBar(
        title = { Text(text = cityName) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickChangeFavouriteStatus() }) {
                val icon = if (isFavourite) {
                    Icons.Default.Star
                } else {
                    Icons.Default.StarBorder
                }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    )
}

@Composable
private fun ApplyContentState(forecast: Forecast) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        CurrentWeather(weather = forecast.currentWeather)

        Spacer(modifier = Modifier.weight(1f))

        UpcomingWeather(upcoming = forecast.upcoming)

        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CurrentWeather(weather: Weather) {
    Text(
        text = weather.condition,
        style = MaterialTheme.typography.titleLarge
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = weather.temp.tempToFormattedString(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 72.sp)
        )

        GlideImage(
            modifier = Modifier.size(64.dp),
            model = weather.conditionUrl,
            contentDescription = null
        )
    }

    Text(
        text = weather.date.formattedDate(),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun UpcomingWeather(upcoming: List<ForecastWeather>) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(
                alpha = 0.24f
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
        ) {
            Text(
                text = stringResource(R.string.upcoming),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                upcoming.forEach {
                    UpcomingWeatherCard(weather = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun RowScope.UpcomingWeatherCard(weather: ForecastWeather) {

    Card(
        modifier = Modifier
            .height(128.dp)
            .weight(1f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formattedUpcomingWeather(weather.minTemp, weather.maxTemp))

            GlideImage(
                modifier = Modifier.size(48.dp),
                model = weather.conditionUrl,
                contentDescription = null
            )

            Text(text = weather.date.formattedDayOfWeek())
        }
    }
}
@Composable
private fun ApplyLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
