const clientURL = "http://localhost:8080/client";
const accountURL = "http://localhost:8080/account";

//pega os params da URL
const params = new URLSearchParams(window.location.search);
const clientId = params.get('id');
const clientLogin = params.get('login')


let userName = document.getElementById("userName");
userName.innerHTML = clientLogin;



//Colunas de obj
const favorites = document.getElementById("columnFav");
const comuns = document.getElementById("columnComum");

async function getAll(id) {
    try{
        //traz o cliente
        const response = await fetch(`${clientURL}/${clientId}`)
        const client = await response.json();
        console.log(client)

        //Pega os accounts do cliente
        client.clientAccounts.map((account) =>{

            let box = document.createElement('div');
            box.classList.add("site-box");

            let title = document.createElement("h3");
            title.innerHTML = account.webName;
            box.appendChild(title);

            let btnFavorite = document.createElement("button")
            btnFavorite.classList.add("btnFavorite")
            btnFavorite.innerText = "FAV"

            btnFavorite.addEventListener("click", () =>{
                actionFavorite(account.id);
            })
            box.appendChild(btnFavorite)




            favorites.appendChild(box);

        })
    }catch(erro){
        console.error("ERRO NA REQUISIÇÃO GETALL:", erro)
    }
    
}
getAll(clientId)

async function actionFavorite(accountId) {
    alert("test")
    
}