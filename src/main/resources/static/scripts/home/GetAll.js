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


        if(!response.ok){
            throw new Error("Erro no retorno da requisição");
        }
        const client = await response.json();
        console.log(client)

    

        //Pega os accounts do cliente
        client.clientAccounts.map((account) =>{

            let box = document.createElement('div');
            box.classList.add("site-box");
            box.addEventListener("click", () =>{
                viewDetails(account)
            })

            let title = document.createElement("h3");
            title.innerHTML = account.webName;
            box.appendChild(title);

            let btnFavorite = document.createElement("button")
            btnFavorite.classList.add("btnFavorite")
            btnFavorite.innerText = "FAV"

            btnFavorite.addEventListener("click", () =>{
                actionFavorite(account.id, account.favorite);
            })
            box.appendChild(btnFavorite)




           !account.favorite ? comuns.appendChild(box) :favorites.appendChild(box);

        })
    }catch(erro){
        console.error("ERRO NA REQUISIÇÃO GETALL:", erro)
    }
    
}
getAll(clientId)


//Função de favoritar ou desfavoritar os dados
async function actionFavorite(accountId, isFavorite) {
    try{
        const response = await fetch(`${accountURL}/${accountId}`,{
            method: 'PATCH',
            headers: {
                    'Content-Type': 'application/json'
                },
            body: JSON.stringify({ favorite: !isFavorite ? true : false })
        })

        window.location.reload()

    }catch(erro){
        console.error("ERRO NA REQUISIÇÃO DE FAVORITAR:", erro)

    }
}

const cardDetails = document.getElementById("viewDetails");

function viewDetails(account) {
   cardDetails.style.display = "block"

   let name = document.getElementById("dtlName").innerText = account.webName;
   let link = document.getElementById("dtlLink").innerText = account.webLink;
   let email = document.getElementById("dtlEmail").innerText = account.email;
   let pass = document.getElementById("dtlPass").innerText = account.password;

}

function Sair(){
  const card = event.target.closest("div"); 
       card.style.display = "none"

}
