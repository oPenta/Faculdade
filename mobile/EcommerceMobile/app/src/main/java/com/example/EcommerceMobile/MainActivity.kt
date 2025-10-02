package com.example.EcommerceMobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.EcommerceMobile.ui.theme.AulaTelasTheme
import com.example.EcommerceMobile.ui.theme.green
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.filled.Description
import androidx.compose.ui.text.style.TextAlign


// CLASSE DE DADOS
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageResId: Int
)


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AulaTelasTheme {
                App()
            }
        }
    }
}

// CLASSES AUXILIARES DE NAVEGAÇÃO
class BottomAppBarItem(
    val icon: ImageVector,
    val label: String
)

class TopAppBarItem(
    var title: String,
    val icons: List<ImageVector> = emptyList()
)

// ESTRUTURA DE NAVEGAÇÃO
sealed class ScreenItem(
    val topAppBarItem: TopAppBarItem,
    val bottomAppBarItem: BottomAppBarItem

) {
    data object Home : ScreenItem(
        topAppBarItem = TopAppBarItem(
            title = "Início",
            icons = listOf(Icons.Default.AccountCircle, Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(icon = Icons.Default.Home, label = "Início")
    )

    data object Products : ScreenItem( // TELA 1: LISTA DE PRODUTOS
        topAppBarItem = TopAppBarItem(
            title = "Roupas",
            icons = listOf(Icons.Default.ShoppingCart, Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(icon = Icons.Default.List, label = "Lista")
    )

    data object Details : ScreenItem( // TELA 2: DETALHES DO PRODUTO
        topAppBarItem = TopAppBarItem(
            title = "Detalhes",
            icons = listOf(Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(
            icon = Icons.Default.Description,
            label = "Detalhes"
        )
    )

    data object Cart : ScreenItem( // TELA 3: CARRINHO
        topAppBarItem = TopAppBarItem(
            title = "Seu Carrinho",
            icons = listOf(Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(
            icon = Icons.Default.ShoppingCart,
            label = "Carrinho"
        )
    )
}

// FUNÇÃO PRINCIPAL DO APP - GERENCIA O ESTADO
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    val screens = remember {
        listOf(
            ScreenItem.Home,
            ScreenItem.Products,
            ScreenItem.Details,
            ScreenItem.Cart
        )
    }

    var currentScreen by remember {
        mutableStateOf(screens.first())
    }

    // ESTADO: Gerencia o item selecionado para a tela de Detalhes
    var selectedProduct by remember {
        mutableStateOf<Product?>(null)
    }

    // ESTADO DO CARRINHO: Lista de itens
    var cartItems by remember {
        mutableStateOf(listOf<Product>())
    }

    // FUNÇÕES DE GERENCIAMENTO DE ESTADO
    val addItemToCart: (Product) -> Unit = { product ->
        cartItems = cartItems + product
        Log.d("CART", "Item adicionado: ${product.name}. Total: ${cartItems.size}")
    }
    val removeItemFromCart: (Product) -> Unit = { product ->
        val updatedList = cartItems.toMutableList()
        updatedList.remove(product)
        cartItems = updatedList.toList()
        Log.d("CART", "Item removido: ${product.name}. Total: ${cartItems.size}")
    }
    val navigateToDetails: (Product) -> Unit = { product ->
        selectedProduct = product
        currentScreen = ScreenItem.Details
    }

    val pagerState = rememberPagerState {
        screens.size
    }

    LaunchedEffect(currentScreen) {
        pagerState.animateScrollToPage(screens.indexOf(currentScreen))
    }

    LaunchedEffect(pagerState.targetPage) {
        currentScreen = screens[pagerState.targetPage]
        if (currentScreen != ScreenItem.Details) {
            selectedProduct = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = {
                Text(currentScreen.topAppBarItem.title)
            }, actions = {
                Row(
                    Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    currentScreen.topAppBarItem.icons.forEach { icon ->
                        Icon(icon, contentDescription = null)
                    }
                }
            })
        },
        bottomBar = {
            BottomAppBar {
                screens.forEach { screen ->
                    with(screen.bottomAppBarItem) {
                        NavigationBarItem(
                            selected = screen == currentScreen,
                            onClick = {
                                currentScreen = screen
                                if (screen != ScreenItem.Details) {
                                    selectedProduct = null
                                }
                            },
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        }

    ) { innerPadding ->

        HorizontalPager(
            pagerState,
            Modifier.padding(innerPadding)
        ) { page ->
            val item = screens[page]
            when (item) {
                ScreenItem.Products -> ProductsScreen(onProductClick = navigateToDetails)
                ScreenItem.Details -> DetailScreen(
                    product = selectedProduct,
                    onAddToCart = addItemToCart
                )
                ScreenItem.Home -> HomeScreen()
                ScreenItem.Cart -> CartScreen(
                    items = cartItems,
                    onRemoveItem = removeItemFromCart
                )
            }
        }
    }
}

// CONTEÚDO DAS TELAS
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Log.d("HOME", "FAB Clicado! Redirecionar para Produtos") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Icon(Icons.Default.List, contentDescription = "Ver Produtos")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = "E-commerce Logo",
                modifier = Modifier.size(96.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "BEM-VINDO AO",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )

            Text(
                text = "ECOMMERCE MOBILE",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Va para a aba lista para ver os produtos existentes e depois para o carrinho para terminar a compra .",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

// 1. TELA DE LISTA DE PRODUTOS (ROUPAS)
@Composable
fun ProductsScreen(onProductClick: (Product) -> Unit) {
    val products = remember {
        // Produtos de Vestuário (100% Sérios)
        listOf(
            Product(1, "Camisa Algodão", "R$ 89,90", "Conforto e beleza.", R.drawable.camisa_branca),
            Product(2, "Calça Jeans Slim", "R$ 179,00", "Jeans mais justa.", R.drawable.calcaslim),
            Product(3, "Jaqueta Corta-Vento", "R$ 249,99", "Para atividades ao ar livre e dias chuvosos.", R.drawable.cortavento),
            Product(4, "Tênis de corrida", "R$ 350,50", "Tennis bom para correr.", R.drawable.tennis),
            Product(5, "Meias", "R$ 29,90", "Meia de algodão.", R.drawable.meia),
            Product(6, "Moletom", "R$ 199,00", "Quentinha e confortavel, bom para inverno.", R.drawable.canguru),
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(products) { product ->
            ProductListItem(product, onProductClick = onProductClick)
        }
    }
}

@Composable
fun ProductListItem(product: Product, onProductClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick(product) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(40.dp))
            Column(Modifier.padding(start = 16.dp).weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ID: ${product.id}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


// 2. TELA DE DETALHES DO PRODUTO
@Composable
fun DetailScreen(product: Product?, onAddToCart: (Product) -> Unit) {
    if (product == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Clique em um produto na lista", fontSize = 20.sp, color = Color.Gray)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Carrega a imagem do produto
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = "Imagem do ${product.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        // Exibe os dados dinâmicos
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = product.price,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Descrição: ${product.description}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.weight(1f))

        // Botão de Adicionar ao Carrinho
        Button(
            onClick = {
                onAddToCart(product)
                Log.d("Detail", "Adicionado ${product.name}")
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Adicionar ao Carrinho")
        }
    }
}

// 3. TELA DE CARRINHO
@Composable
fun CartScreen(items: List<Product>, onRemoveItem: (Product) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        if (items.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                Text("Seu carrinho nao tem nada", fontSize = 24.sp, color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    CartItemRow(item = item, onRemove = onRemoveItem)
                }
            }

            // Botão de Compra no rodapé
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val total = items.sumOf {
                    // Cálculo simplificado do total
                    it.price.replace("R$", "").replace(",", ".").trim().toDoubleOrNull() ?: 0.0
                }

                Text(
                    text = "Total: R$ ${"%.2f".format(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = {
                        Log.d("CART", "COMPRA FINALIZADA! Total de ${items.size} itens. Valor: R$ ${"%.2f".format(total)}")
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = items.isNotEmpty()
                ) {
                    Text("FINALIZAR COMPRA")
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: Product, onRemove: (Product) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(item.price, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }

            IconButton(onClick = { onRemove(item) }) {
                Icon(Icons.Default.Close, contentDescription = "Remover item")
            }
        }
    }
}


// FUNÇÕES AUXILIARES E PREVIEW
@Composable
fun Example(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = green,
        contentColor = Color.White,
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}


@Preview
@Composable
private fun AppPreview() {
    AulaTelasTheme {
        App()
    }
}