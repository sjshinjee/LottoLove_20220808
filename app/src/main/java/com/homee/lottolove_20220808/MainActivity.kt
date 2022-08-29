package com.homee.lottolove_20220808

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
// 내 번호 6개 저장 coz WinNum6개와 내 번호를 함수
    val mMyNumList = arrayOf(5,10,15,17,24,44)  // 내 번호는 Hard코딩 arrayOf함수이용해서

// 컴퓨터가 뽑은 랜덤 당첨번호 6개를 저장 on ArrayList
    var mWinNumList = ArrayList<Int>()  //나중에 for문으로 돌면서 랜덤값을 뽑아서 ()안에 넣어줄거다
    var mBonusNum = 0 //실제 보너스 번호가 저장될 곳도 미리 세팅

// 랜던 번호 6개를 집어넣을 텍스트뷰 자료형의 ArrayList를 만들자
    val mWinNumViewList = ArrayList<TextView>()

// 사용금액과 당첨금액 그리고 당첨횟수를넣을 멤버변수들
    var mUsedMoney = 0  // m은 member멤버 변수의 약자 & 사용금액도 나중에 TextView에 text로 입력해줄거임
    var mWinMoney = 0L  // Int최댓값은 20억이므로 1등 당첨금액 30억원에 모자르다
                        // So Long 타입으로 설정
    var firstCount = 0
    var secondCount = 0
    var thirdCount = 0
    var fourthCount = 0
    var fifthCount = 0
    var loseCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWinNumViewList.add(winNum1Txt)
        mWinNumViewList.add(winNum2Txt)
        mWinNumViewList.add(winNum3Txt)
        mWinNumViewList.add(winNum4Txt)
        mWinNumViewList.add(winNum5Txt)
        mWinNumViewList.add(winNum6Txt)

        buyLottoBtn.setOnClickListener{
            buyLotto()
        }

        autobuyLottoBtn.setOnClickListener{

        }
    }


    fun buyLotto(){
            mUsedMoney += 1000

        // 1 로또 당첨 번호 6개 선정
        for (i in 0 until 6){
          //등록되도 괜찮은 (중복되지 않은) 숫자가 나올때까지 무한 반복
            while(true){
              //  val random = (Math.random() * 45 +1).toInt() //Java와 마찬가지로 Math클래스 써줌
                val randomNum = (Math.random() * 45 + 1).toInt()  //Java에서 복사에서 convert해서 붙여줌

//                var isRepeat = false
//
//              //for문으로 돌면서 하나의 번호를 mWinNumList에서 뽑을거다
//                /// 이것이 Kotlin에서 for each쓰는 가장 쉬운 방법 = 배열에서 숫자 하나를 뽑는것
//                for (num in mWinNumList){
//                        if (num == randomNum){
//                            isRepeat = true
//                            break  // for문을 탈출하는 구문
//                        }
//                }
//                if(!isRepeat){
//                    mWinNumList.add(randomNum)
//                    break
//                }


        //위 주석처리된 코드를 ArrayList기능 활용해서 3줄로 코드하자 contains함수를 통해서
        // mWinNumList에 이 randomNum이 있는지를 contains함수를 통해서 자기 혼자 알아서 for문을 돌면서 확인함
                if(!mWinNumList.contains(randomNum)){
                    mWinNumList.add(randomNum)
                    break
                }
            }  //while(true)
        }  //for (i in 0 until 6)

        // 2 당첨번호 정렬 (Bubble sort) > TextView에 표현하는 것까지
        mWinNumList.sort() //작은수 ~ 큰수 정리 완료  sort는 List에 들어가 있는 기능
                           ///winNum을 List로 만든이유가 이 sort라든가 contains와 같은 기능들을 활용하려고
            for ((index, num) in mWinNumList.withIndex()){   //주의 index는 사실 integer이다 & 코드해석은
                                                             ///Trello에 Kotlin문법관련 6번 반복문 for 참조
              //텍스트뷰 변수를 가져와서 text속성으로 넣기
              //텍스트뷰들을 ArrayList의 재료
              // for each문 역시 Index값을 같이 활용하는 for문 작성 / for문에 .찍고 withIndex()기능 활용
                mWinNumViewList[index].text = num.toString()
            }

        // 3 보너스 번호 하나 선정 > 텍스트 뷰에 배치
        while(true) {
            val randomNum = (Math.random() * 45 +1).toInt()

            if(!mWinNumList.contains(randomNum)){
                mBonusNum = randomNum
                bonusNumTxt.text = mBonusNum.toString()
                break
            }
        }
        fun checkLotto()
    } // fun buyLotto()

        fun checkLotto(){
        // 4 비교
                var correctCount = 0 //멤버변수가 아닌 이유는 한 번 체크할 때마다 결국엔 무조건 0으로 초기화 해줘야함
            // 내 번호를 하나씩 조회 for each문 이용해서
            for(myNum in mMyNumList){ //내 번호가 들어있는 Array에서 하나씩 뽑는거다
//                for(winNum in mWinNumList){
//                    if(muNum == winNum ){
//                        correctCount++
//                    }
//                }
                // 위의 코드는 코드가 길어지므로 간단한 코드로 작성
                ///Coz 우리가 궁금한 것은 당첨 번호를 맞췄는가 이고 이 뜻은 당첨번호 목록에 내 번호가 들어있는지 알면 된다
                if(mWinNumList.contains(myNum)){
                    correctCount++
                }
            }
        // 5 순위 선정 (맞춘 개수에 따라) > 등수 판단 하고 텍스트 뷰에 출력
        ///Java에서는 if문으로 작성했는데 여기서는 when문 활용
            when(correctCount){
                6 -> {
                    //1등 당첨
                    mWinMoney += 3000000000
                    firstCount++
                }
                5 -> {
                    //보너스 번호를 맞췄는지 = 보너스 번호가 내 번호 목록에 들어있나
                    if(mMyNumList.contains(mBonusNum)){
                        // 가지고 있으면 2등
                        mWinMoney += 50000000
                        secondCount++
                    }
                    else{
                        mWinMoney += 2000000
                        thirdCount++
                    }
                }
                4 -> {
                    mWinMoney += 50000
                    fourthCount++
                }
                3 -> {
                    mWinMoney += 5000
                    fifthCount++
                }
                else -> {
                    loseCount++
                }
            }

            //마지막으로 사용금액 당첨금액 당첨횟수를 텍스트뷰에 각각 반영
        }


} //class MainActivity : AppCompatActivity()
