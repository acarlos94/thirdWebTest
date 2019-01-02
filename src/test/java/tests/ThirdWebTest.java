package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThirdWebTest {
    @Test
    public void trivagoTest() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Antonio\\IdeaProjects\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        navegador.get("https://www.trivago.com.br/");
        pesquisaCidade(navegador);
        escolheQuarto(navegador);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filtraResultado(navegador);
        imprimirSegundoResultado(navegador);
        maisOfertas(navegador);
    }

    public void maisOfertas(WebDriver navegador) {
        WebElement maisOfertas = navegador.findElement(By.className("slideout-deals"));
        List<WebElement> ofertas = maisOfertas.findElements(By.className("slideout-deals__item"));
        if (ofertas.size() > 2){
            WebElement terceiraOferta = ofertas.get(2);
            WebElement logo = terceiraOferta.findElement(By.className("slideout-deal__logo"));
            String nomeSite = logo.findElement(By.tagName("strong")).getText();
            System.out.println(nomeSite);
            try {
                String infoQuarto = terceiraOferta.findElement(By.className("slideout-deal__description")).getText();
                String valorQuarto = terceiraOferta.findElement(By.className("slideout-deal__price")).getText();
                System.out.println(infoQuarto);
                System.out.println("Preço: " + valorQuarto);
            } catch (Exception e){
                System.out.println("Este site não disponibilizou informações");
            }
        }else {
            System.out.println("Não foi encontrada uma terceira oferta");
        }
    }

    public void imprimirSegundoResultado(WebDriver navegador) {
        WebElement resultado = navegador.findElement(By.id("js_item_list_container"));
        List<WebElement> hoteis = resultado.findElements(By.className("item-order__list-item"));
        WebElement segundaOpcao = hoteis.get(1);
        String nomeHotel = segundaOpcao.findElement(By.className("name__copytext")).getText();
        System.out.println("Nome do hotel: " + nomeHotel);
        List<WebElement> estrelas = segundaOpcao.findElements(By.className("item__stars-wrp"));
        System.out.println("Quantidade de estrelas: " + estrelas.size());
        segundaOpcao.findElement(By.className("deal-other__more")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void filtraResultado(WebDriver navegador) {
        WebElement ordenacao = navegador.findElement(By.id("mf-select-sortby"));
        ordenacao.click();
        List<WebElement> opcoes = ordenacao.findElements(By.tagName("option"));
        for (WebElement opcao : opcoes){
            if (opcao.getText().equals("Somente distância")){
                opcao.click();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void escolheQuarto(WebDriver navegador) {
        navegador.findElement(By.className("horus__btn-detail--roomtype")).click();
        List<WebElement> tiposQuarto = navegador.findElements(By.className("roomtype-btn__label"));
        for (WebElement tipoQuarto : tiposQuarto){
            if (tipoQuarto.getText().equals("Quarto individual")){
                tipoQuarto.click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        navegador.findElement(By.className("horus-btn-search__label")).click();
    }

    public void pesquisaCidade(WebDriver navegador) {
        navegador.findElement(By.id("horus-querytext")).sendKeys("N");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navegador.findElement(By.id("horus-querytext")).clear();
        navegador.findElement(By.id("horus-querytext")).sendKeys("Natal");
        navegador.findElement(By.className("horus-btn-search__label")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
