package OYOBooking;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;


public class Main extends Application 
{
	protected void configure(final Configuration config) 
    {
    	config.setTitle(" OYO ROOMS ");
    	config.setFullScreen(false);
    }

	public static void main(String[] args) 
	{
		Main main = new Main();
		launch(main);

	}

	@Override
	public void process() 
	{
		ImGui.begin("Login");
		ImGui.end();
		
	}

}
