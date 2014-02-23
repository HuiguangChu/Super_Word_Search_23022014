package wordsearch;
/**
 * @author huiguang chu
 * @date   23.02.2014
 * @palce Gjøvik, Norway
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

public class WordsearchGame {
	public static Text text;
	private static Text text_1;
	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setMinimumSize(new Point(400, 300));
		shell.setSize(500, 400);
		shell.setText("Super Word Game    Huiguang Chu    23.02.2014");
		
		/* define open file button*/
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(53, 10, 75, 25);
		btnNewButton.setText("Open");
		
		/* define textbox for display the read-in file content*/
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.CENTER);
		text.setBounds(52, 62, 182, 207);
		
		/*display the read-in file content*/
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("resource")
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
			      String fileName = dlg.open();
			      try{
			    	  
			    	  if(fileName!=null){
			    		  FileInputStream fis = new FileInputStream(fileName);
			    		  text.setText("");
			    		  BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			    		  String s = null;
			    		  while ((s = in.readLine()) != null)
			    			  text.append(s + "\n");  
			    		  }
			      }catch (Exception e1){
			    	  MessageDialog.openError(null, "Error", "Can not open file");
			      		}
			}
		});
		
		
		/* program execute button*/
		Button btn = new Button(shell, SWT.CENTER);
		btn.setBounds(207, 280, 75, 25);
		btn.setText("Go");
		
		/*textbox used for dislaying the search result*/
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.PASSWORD | SWT.CANCEL | SWT.CENTER | SWT.MULTI);
		text_1.setBounds(259, 62, 182, 207);
		
		/* search word functionality*/
		btn.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	WordGridBuilder builder =new WordGridBuilder();
				WordFileReader reader = new WordFileReader(builder);
				reader.readInput();
				WordSearch word = builder.builder();
				word.search();
				text_1.setText("");
				for(int i =0; i< word.getSearchResults().size(); i++){
					if (word.getSearchResults().get(i)!=null){
					text_1.append((word.getSearchResults().get(i))+"\n");
					}
					else{
						text_1.append("NOT FOUND"+"\n");
						}
				}
		    }
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
