/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.EyeService;
import thanhtrang.online.thanhtrang.Model.Receipt;

public class SendPostWithHttpClient {
    public void SendRequest(EyeService eyeService, Customer c, Receipt r) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://nodered.thanhtrang.online/api/notification"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + c.getName() + "\",\"phone\":\"" + c.getPhone() + "\","
                            + "\"totalpay\":\"" + (r.getTkPrice() + r.getGkPrice()) + "\",\"time\":\"" + r.getDate() + "\"}"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}