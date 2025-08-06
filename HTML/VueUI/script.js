const { createApp } = Vue;

createApp({
    data() {
        return {
            produtos: [
                { titulo: 'iPhone 15 Pro Max' },
                { titulo: 'Samsung Galaxy S24 Ultra' },
                { titulo: 'MacBook Pro M3 Pro 14"' }
            ],
            
            mostrarElemento: true,
            
            mensagem: 'Digite aqui...'
        }
    }
}).mount('#app');