
package CrudAppProjeto;

public class Info {
     private int id;
    private String senhas, cpf, emails, cartaoSus, numeroDoCartao;

    public Info(int id,String senhas, String cpf, String emails, String cartaoSus, String numeroDoCartao) {
        this.senhas = senhas;
        this.id = id;
        this.cpf = cpf;
        this.emails = emails;
        this.cartaoSus = cartaoSus;
        this.numeroDoCartao = numeroDoCartao;
        
        
    }

    public String getSenhas() {
        return senhas;
    }

    public void setSenhas(String senhas) {
        this.senhas = senhas;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }
   
   
}
