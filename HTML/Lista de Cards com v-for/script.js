const { createApp } = Vue;

createApp({
  data() {
    return {
        produtos: [
    {
        titulo: 'iPhone 15 Pro Max',
        descricao: 'Tela 6.7" Super Retina XDR, A17 Pro, 48MP, 5G, 256GB - Apple'
    },
    {
        titulo: 'Samsung Galaxy S24 Ultra',
        descricao: 'Tela 6.8" Dynamic AMOLED, Snapdragon 8 Gen 3, 200MP, S Pen - 512GB'
    },
    {
        titulo: 'MacBook Pro M3 Pro 14"',
        descricao: 'Chip M3 Pro, 16GB RAM, 512GB SSD, XDR 120Hz - Space Black'
    },
    {
        titulo: 'iPad Pro 11" M2',
        descricao: 'Chip M2, Liquid Retina 120Hz, Thunderbolt, 256GB - Wi-Fi + 5G'
    },
    {
        titulo: 'Apple Watch Ultra 2',
        descricao: 'Tela 49mm, GPS + Cellular, resistente 100m, watchOS 10'
    },
    {
        titulo: 'Logitech G203',
        descricao: 'Mouse ergonômico 8K DPI, scroll MagSpeed, multi-device - Preto'
    },
    {
        titulo: 'Monitor LG UltraFine 32UN880',
        descricao: '4K UHD 32", USB-C 90W, HDR400, ajuste ergonômico - Para Mac/PC'
    },
    {
        titulo: 'SSD Samsung T7 Shield 2TB',
        descricao: 'Portátil USB 3.2, resistente IP65, até 1050MB/s - Preto'
    },
    {
        titulo: 'GoPro Hero 12 Black',
        descricao: 'Câmera ação 5.3K60, HyperSmooth 6.0, HDR, à prova d\'água'
    },
    {
        titulo: 'Nintendo Switch OLED',
        descricao: 'Tela OLED 7", 64GB, dock 4K, Joy-Cons - Edição Neon'
    },
    {
        titulo: 'Echo Dot (5ª geração)',
        descricao: 'Smart speaker Alexa, Bluetooth, design compacto - Preto'
    },
    {
        titulo: 'Xiaomi Smart Band 8 Pro',
        descricao: 'Smartband 1.74" AMOLED, SpO2, 150+ modos esporte - Global Version'
    },
    ]
    };
  },
  methods: {
    comprar(produto) {
      alert(`Adicionado ao carrinho: ${produto.titulo}`);
    }
  }
}).mount('#app');