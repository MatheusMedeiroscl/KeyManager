const clientURL = "http://localhost:8080/client";
const accountURL = "http://localhost:8080/account";

//pega os params da URL
const params = new URLSearchParams(window.location.search);
const clientId = params.get('id');
const clientLogin = params.get('login')

let userName = document.getElementById("userName");
userName.innerHTML = clientLogin;
let selectedAccount = null;
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
            box.classList.add("siteBox");

            let titleBox = document.createElement('div');
            titleBox.classList.add("titleBox")
            titleBox.addEventListener("click", () =>{
                viewDetails(account)
            })

            let btnBox = document.createElement('div');
            btnBox.classList.add("btnBox")
        

            box.appendChild(titleBox);
            box.appendChild(btnBox);

            let title = document.createElement("h3");
            title.innerHTML = account.webName;
            

            titleBox.appendChild(title);

            let btnFavorite = document.createElement("img")
            btnFavorite.classList.add("btnFavorite")
            
            !account.favorite ? btnFavorite.src = "../styles/icons/NonStar.png" :btnFavorite.src = "../styles/icons/FavStar.png";

            btnFavorite.addEventListener("click", () =>{
                actionFavorite(account.id, account.favorite);
            })
            btnBox.appendChild(btnFavorite)

         
            let btnDelete = document.createElement("img");
            btnDelete.classList.add("btnDelete");
            btnDelete.src = "../styles/icons/Delete.png"

            btnDelete.addEventListener("click", () =>{
                actionDelete(account.id);
            })

            btnBox.appendChild(btnDelete)

            let btnCopy = document.createElement("img");
            btnCopy.classList.add("btnCopy")
            btnCopy.src = "../styles/icons/Copy.png"

            btnCopy.addEventListener("click", () =>{
              let copyPass = account.password;
              navigator.clipboard.writeText(copyPass);
              alert("Senha copiada")
            })

            btnBox.appendChild(btnCopy)


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
const cardUpdate = document.getElementById("cardEdit")
function viewDetails(account) {
   cardDetails.style.display = "block"

   selectedAccount = account

   let name = document.getElementById("dtlName").innerText = account.webName;
   let link = document.getElementById("dtlLink").innerText = account.webLink;
   let email = document.getElementById("dtlEmail").innerText = account.email;
   let pass = document.getElementById("dtlPass").innerText = account.password;

   let btnCopy = document.getElementById("btnCopy");
            btnCopy.addEventListener("click", () =>{
              let copyPass = account.password;
              navigator.clipboard.writeText(copyPass);
              alert("Senha copiada")
            })
    
    let btnEdit = document.getElementById("btnEdit");
            btnEdit.addEventListener("click", () =>{
                cardDetails.style.display = "none"

                let name = document.getElementById("txtNameEdited").value = selectedAccount.webName;
                let link = document.getElementById("txtLinkEdited").value = selectedAccount.webLink;
                let email = document.getElementById("txtEmailEdited").value = selectedAccount.email;
                let pass = document.getElementById("txtPassEdited").value = selectedAccount.password;

                cardUpdate.style.display = "block"
            })
}

const updateForm = document.getElementById("formEdit").addEventListener("submit", (e) =>{
    e.preventDefault();

     let name = document.getElementById("txtNameEdited").value
    let link = document.getElementById("txtLinkEdited").value
    let email = document.getElementById("txtEmailEdited").value
    let pass = document.getElementById("txtPassEdited").value


    const accountEdited = {
        webName: name,
        webLink: link,
        email: email,
        password: pass,
        favorite: selectedAccount.favorite
    }
    update(accountEdited)
    cardUpdate.style.display = "none"
    window.location.reload()



})

async function update(account) {
          const response = await fetch(`${accountURL}/${selectedAccount.id}`, {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json' // Tipo de conteúdo sendo enviado
            }, 
            body: JSON.stringify(account)
          })
        .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao atualizar a conta');
        }
        return response.json();
        })
        .then(data => {
        console.log('Conta atualizada com sucesso:', data);
        })
        .catch(error => {
        console.error('Erro na atualização:', error);
        });
         

        alert("Conta Atualizada")
   
    }


//Func de criar 
const btnAccessCreate = document.getElementById("btnCreate")
const cardCreate = document.getElementById("cardCreate")
btnAccessCreate.addEventListener("click", () =>{
    cardCreate.style.display = "block"

    const createForm = document.getElementById("formCreate").addEventListener("submit", (e) =>{
        e.preventDefault()

        let name = document.getElementById("txtName").value
        let link = document.getElementById("txtLink").value
        let email = document.getElementById("txtEmail").value
        let pass = document.getElementById("txtPass").value

        const newAccount = {
            client: {
                id: clientId // aqui você usa a variável com o valor do ID
            },
            webName: name,
            webLink: link,
            email: email,
            password: pass,
            favorite: false
         }
        create(newAccount)
        cardCreate.style.display = "none"
        window.location.reload()
    })

})

async function create(account) {

    try{
     const response = await fetch(accountURL, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json' 
            }, 
            body: JSON.stringify(account)
          })

          if(!response.ok){
              throw new Error(`ERRO AO CRIAR CONTA: ${response.status} - ${response.statusText}`);
          }

          alert ("DADOS CRIADOS COM SUCESSO!")
    } catch (error) {
            console.error("ERRO EM FAZER A REQ DE CRIAR:", error);

    }
}



//Func de sair
function Sair(){
  const card = event.target.closest("div"); 
       card.style.display = "none"

}

//Func de Dell
async function actionDelete(id){
    try{
        const response = await fetch(`${accountURL}/${id}`,{
            method: "DELETE"
     })
     window.location.reload()

    }catch(erro){
        console.error("ERRO NA REQUISIÇÃO DE DELETAR:", erro)
    }
}



