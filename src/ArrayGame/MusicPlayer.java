package ArrayGame;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;


public class MusicPlayer extends Thread{ 
	//Reference: https://code-question.com/developer-blog/java-implementation-of-a-simple-music-player-1
	private int note;
	static int[] NOTES = {0,2,4,5,7,9,11};
	
	public MusicPlayer(int note){
		super();
		this.note = note;
	}
	
	
	
	@Override
	public  void run() {

		Sequencer player;
		try {
			
			player = MidiSystem.getSequencer();
			player.open();
			player.getSequence();
			Sequence seq = new Sequence(Sequence.PPQ, 1);
			
			Track track = seq.createTrack();

			
			 ShortMessage a = new ShortMessage();
			 a.setMessage(144, 1, this.note,100);
			 MidiEvent noteOn = new MidiEvent(a, 0);
			 track.add(noteOn);



		    player.setSequence(seq); // Give the sequence to the Sequencer
		                             // like pushing a CD to a CD player
		    player.start();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
