const URL = "http://localhost:8080/client/login";

let txtLogin = document.getElementById("txtLogin");
let txtPass = document.getElementById("txtPass");

const form = document.getElementById("Login");

form.addEventListener("submit", (e) => {
    e.preventDefault()

    let login = txtLogin.value;
    let pass = txtPass.value;

    async function Acess() {
        try{
            const response = await fetch(`${URL}/${login}`);

            if(!response.ok){
                throw new Error(`ERRO AO FAZER A CONSULTA: ${response.status} - ${response.statusText}`);
            }else{
                const data = await response.json();
                window.location.href = `home.html?id=${data.id}&login=${data.login}`;

            }
        }catch(error){
            console.log("ERROR AO PROCURAR O CLIENT" + Error);
            alert("erro ao procurar o client");
        }
        
        
    }

    Acess()
})