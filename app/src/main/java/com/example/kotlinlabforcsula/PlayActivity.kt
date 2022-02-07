//import android.content.Intent
//////Declaring a private static Button variable we will be used in this Activity
////private lateinit var playButton:Button;
////The main activity will be the first activity to start when opening the app
////class MainActivity : AppCompatActivity() {
////    //When the main activity starts the onCreate function will be called
////    override fun onCreate(savedInstanceState: Bundle?) {
////
////        super.onCreate(savedInstanceState)
//////Refers to the layout xml page we just created above
////        setContentView(R.layout.activity_main)
//////Elements we want to refer to in the layout page are given a unique id
//////below we are looking for the btn_play id in the layout sheet using
////        findViewById
//////and initializing the playButton variable to refer to it
////        playButton = findViewById(R.id.btn_play)
//////Setting up the btn_play button's functionality in the layout
////        playButton.setOnClickListener{
//////changing the activity to playActivity
//////we will create this in the next part so for now you should see a
////            red line below
////            val intent = Intent(this, PlayActivity::class.java)
////            startActivity(intent)
////        }
////    }

package com.example.kotlinlabforcsula


        import android.graphics.Color
                import androidx.appcompat.app.AppCompatActivity
                import android.os.Bundle
                import android.widget.Button
                import android.widget.TextView
//Declaring the variables needed in this section
                private lateinit var boxes:Array<Button>//Array of buttons
        private lateinit var btn_reset:Button
        private lateinit var tv_turn:TextView
        private lateinit var tv_player1Score:TextView
        private lateinit var tv_player2Score:TextView
        private var turns = 0
        private var gameWon = false;
        private var player1Turn = true
        private var playerOneScore = 0
        private var playerTwoScore = 0
        private lateinit var resetButton:Button
        class PlayActivity : AppCompatActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_play);
//Setting up the scoring system
                tv_player1Score = findViewById(R.id.tv_player1Score)
                tv_player1Score.setText(playerOneScore.toString())
                tv_player2Score = findViewById(R.id.tv_player2Score)
                tv_player2Score.setText(playerTwoScore.toString())
//initializing the array of buttons
                boxes = arrayOf(
                            findViewById(R.id.btn_box0),findViewById(R.id.btn_box1),findViewById(R.id.btn_box2),
                    findViewById(R.id.btn_box3),findViewById(R.id.btn_box4),findViewById(R.id.btn_box5),
                    findViewById(R.id.btn_box6),findViewById(R.id.btn_box7),findViewById(R.id.btn_box8))
//setting up the functions for each button/box of the game
                boxes.forEach {btn ->
                    btn.setOnClickListener {
                        takeTurn(btn)
                        if(turns == 9 &&!gameWon){
                            tv_turn.setText("Tie Game");
                        }
                    }
                }
                tv_turn = findViewById(R.id.tv_turn)
//The game will start with Player 1.
// This just makes sure the text in tv_turn matches
                tv_turn.setText("Turn: Player 1")
                btn_reset = findViewById(R.id.btn_reset)
                btn_reset.setOnClickListener {
                    reset()
                }
            }
            fun takeTurn(btn: Button){
                if(btn.text != "" || gameWon){
                    return
                } else if(player1Turn) {
                    btn.setText("X")
                    tv_turn.setText("Turn: Player 2")
                    player1Turn = false;
                }
                else {
                    btn.setText("O")
                    tv_turn.setText("Turn: Player 1")
                    player1Turn = true
                }
                turns++;
                if(checkWinner()){
                    gameWon = true
                    var winner:Int
                    if(player1Turn){
                        winner = 2
                        playerTwoScore++
                        tv_player2Score.setText(playerTwoScore.toString())
                    } else {
                        winner = 1
                        playerOneScore++
                        tv_player1Score.setText(playerOneScore.toString())
                    }
                    tv_turn.setText("Player ${winner} Wins!!!")
                }
            }
            fun checkWinner():Boolean{
                if(boxes[0].text == boxes[4].text && boxes[0].text == boxes[8].text &&
                    boxes[4].text != ""){
                    boxes[0].setBackgroundColor(Color.GREEN)
                    boxes[4].setBackgroundColor(Color.GREEN)
                    boxes[8].setBackgroundColor(Color.GREEN)
                    return true
                } else if(boxes[2].text == boxes[4].text && boxes[2].text ==
                    boxes[6].text && boxes[4].text != ""){
                    boxes[2].setBackgroundColor(Color.GREEN)
                    boxes[4].setBackgroundColor(Color.GREEN)
                    boxes[6].setBackgroundColor(Color.GREEN)
                    return true
                }
                for(i in 0..8 step 3){
                    if(boxes[i].text == boxes[i+1].text && boxes[i].text ==
                        boxes[i+2].text && boxes[i].text != ""){
                        boxes[i].setBackgroundColor(Color.GREEN)
                        boxes[i+1].setBackgroundColor(Color.GREEN)
                        boxes[i+2].setBackgroundColor(Color.GREEN)
                        return true;
                    }
                }; for(i
                in 0..2){
                    if(boxes[i].text == boxes[i+3].text && boxes[i+3].text ==
                        boxes[i+6].text && boxes[i].text != ""){
                        boxes[i].setBackgroundColor(Color.GREEN)
                        boxes[i+3].setBackgroundColor(Color.GREEN)
                        boxes[i+6].setBackgroundColor(Color.GREEN)
                        return true;
                    }
                }
                return false
            }
            fun reset(){
                clearBoard()
                gameWon = false
            }
            fun clearBoard(){
                boxes.forEach {btn ->
                    btn.setText("")
                    btn.setBackgroundColor(Color.GRAY)
                    turns = 0
                }
            }
        }




//class PlayActivity : AppCompatActivity() {
//
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityPlayBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityPlayBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_play)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_play)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//}

