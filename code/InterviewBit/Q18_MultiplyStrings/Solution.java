package Q18_MultiplyStrings;

public class Solution {
  public String multiply(String A, String B) {
    StringBuilder n1 = new StringBuilder(A).reverse();
    StringBuilder n2 = new StringBuilder(B).reverse();

    int[] d = new int[A.length() + B.length()];

    // multiply each digit and sum at the corresponding positions
    for (int i = 0; i < n1.length(); i++) {
      for (int j = 0; j < n2.length(); j++) {
        d[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
      }
    }

    StringBuilder sb = new StringBuilder();

    // calculate each digit
    for (int i = 0; i < d.length; i++) {
      int mod = d[i] % 10;
      int carry = d[i] / 10;
      if (i + 1 < d.length) {
        d[i + 1] += carry;
      }
      sb.insert(0, mod);
    }

    // remove leading 0's
    while (sb.charAt(0) == '0' && sb.length() > 1) {
      sb.deleteCharAt(0);
    }

    return sb.toString();
  }

  public String multiplyBruteForce(String A, String B) {
    String ans = "0";
    for (int i = 0; i < A.length(); i++) {
      char a = A.charAt(i);
      int trailingZeroA = A.length() - i - 1;
      for (int j = 0; j < B.length(); j++) {
        int trailingZeroB = B.length() - j - 1;
        char b = B.charAt(j);

        String ab = multiply(a, b, trailingZeroA + trailingZeroB);
        // System.out.println("i: " + i + ", j: " + j + " = " + ab);
        ans = add(ans, ab);
      }
    }

    return ans;
  }

  private String add(String a, String b) {
    if (a.length() > b.length()) {
      String temp = a;
      a = b;
      b = temp;
    }

    char[] chars = new char[b.length()];
    int carry = 0;

    for (int i = 0; i < chars.length; i++) {
      int position = a.length() - i - 1;
      int digitA = position >= 0 ? a.charAt(position) - '0' : 0;

      position = b.length() - i - 1;
      int digitB = b.charAt(position) - '0';

      int sum = digitA + digitB + carry;
      chars[position] = (char) ('0' + sum % 10);
      carry = sum / 10;
    }
    StringBuilder sb = new StringBuilder();
    if (carry > 0) {
      sb.append(carry);
    }
    boolean trimmingLeadingZero = carry == 0;
    for (char c : chars) {
      if (trimmingLeadingZero) {
        if (c == '0') {
          continue;
        } else {
          trimmingLeadingZero = false;
        }
      }
      sb.append(c);
    }
    if (trimmingLeadingZero) {
      sb.append('0');
    }
    return sb.toString();
  }

  private String multiply(char a, char b, int trailingZero) {
    int ans = (a - '0') * (b - '0');
    StringBuilder sb = new StringBuilder();
    sb.append(ans);
    while (trailingZero-- > 0) {
      sb.append("0");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    String a = "12";
    String b = "10";
    String ans = s.multiply(a, b);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"120".equals(ans)) {
      throw new AssertionError();
    }

    a = "12345678";
    b = "87";
    ans = s.multiply(a, b);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"1074073986".equals(ans)) {
      throw new AssertionError();
    }

    a = "0012345678";
    b = "0087";
    ans = s.multiply(a, b);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"1074073986".equals(ans)) {
      throw new AssertionError();
    }

    a = "001234567800000000";
    b = "0087000000";
    ans = s.multiply(a, b);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"107407398600000000000000".equals(ans)) {
      throw new AssertionError();
    }

    a = "31243242535342";
    b = "0";
    ans = s.multiply(a, b);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"0".equals(ans)) {
      throw new AssertionError();
    }

    a =
        "0658233826335581483250777635170745800383991107793847226241731771058610585518689700161460324306862295276501940111464507659968619631876170050860808136667358040048283549292563830028668439574455233084067387161844544110508139638120106117239002277861698315766426684793587265919716529616239060110174059556638403074994438056034825391847931159942570215195360696101972580445888625759629090992381077218083420631597759284998798955434979813554056113142057184085862776708229951029534815322473612900997097515507823494690621047595415334181202666457438068336077040165951423415171700455192696701639771109641311341614764217875374690349302627112890177978681965059031942659633838495143321979647058296117068085651612334039329573084274099540797622505001496719471202768841950166519070070743816669537628690879835157256807843189349143145785153234004431871883733260024756987106508952124235937091897994067330770599946542542510440437697595274158915390219945374533349365539572425926945434302784990490057936536337485523061262782819535191371205336684174269989707055344514745578542526307360131623076616028499725893653565887132791992535190025472867808883685641773289619396385573711638001306754980669746306912649394264488226881054707323428789379981822248346295668443535452182843072876726172525715706872570934248631502873752635568634178970226257853652635167934019477356363590477570866970996431593362257261307319402857109135989456023432064942220271841178407941210440943853104517412726538422711501213332296375508267444273075074837567928320018741728490665371667391530938212346712114146602352277941360800329023295360510147095313152788500930668037125963143010945147618156403580795123242040257930630871695626991778102210221736947396275131245711400861647698675897438572836353532461997615133292347568070652814983188265568020428942972604650891020725523638645825512123130306797394334282863466023639874587217664131228753754065957200557186849980035742334621379756783217152305648579317104661501838504636909762660309514685377519298409969646646081556350560182445822508941641806422915739820523643353278933954110491525799598035100690051628752374224125688621438212259725580547152736663750175118463976704747616290208727786768608955079786381527590344263774104183371385687473813334584854922467560813026670193104075904034990051022042458468135976514958660418856609882730599278353494747644787747567607251905206435217603389869518416225609400782666119313044930121514551584341701410471753424008033807462251825785671801161931817975648878183465029089433773517700257819095692087411153816211110590300713132938224257144722837731949935256394960705215033867933770876059665419918545636909760915224619239898164669827966851304542384138876007760254219422527431850596746462732791150043848608986404933037924049221458092849484224245633748511521547882638647378010130361416948850214403713208741197631269516686393515928729120814816072609829837420828280930127939806704061742065519948180751966022985018567722958200982566226718231842936006046910599451917617246618686465702387822089358446669417917195481583829743718984664896875315730491502195943656656348761104528198363324194191218903649020984399408386147601350073129055201049449870366791057646121253110317620612767640810374957660862139243326896356931019930269738583843235284535255460334677002669933115829775717840800106484797301267989967649221783067212004507779759519552999122936986564919566829117926758068081743132421374322639620991738697126040220664043462661498309659375697291199358088378937445282657263548614811125350948376593343692848012133009040016254451015312122840962308431528581655702337149153399734615266785595616629866170747846846238584987342459439346676807658893831609872634278884177447691189176633558169926180764661163436514528190456300497440856935853561216737213870581181156623750121304882491347837567814719650351211301507754672763480991583109952657157977698206226445427530777754291271759424197409365778275275255762662664467358748794854529137667748984455804632807027667272244975744283888796772988713241674074978890413624455007455855235643485675388456149880879977543620348566122738318899132949628077059882376206400836995692301038881418901542832344671597723646329197098705433697384660125570040103212300862997392912031964811411543571856769877289807428767879541037592591850330012737064916692924291333199180950936075992003705106204529114768723613488837483576459047310824300960653113696643699302324919938766329931334108468971967407959317829782852789024638354117351579641807537414754372742639422099419794805404627060307831494520965961493973786828948003858468442282774938955278720382628718698525243064601346426436141632299942648087853750369793893691814210361833272243666924867433893794087868364653404597708028579528755466032590916957058280888152541527043522410182172604817065852001866680777965673272943653515440173325584300143839884182775883447272630953429151599018646782708711793641676467191647357581503199241810112354092128932226394298136547535324569390613959425380164368026121330601828176617210589189108321964901153775009639824799895138729";
    b = "912368301888368022394840960108863800439774650725209850681532166112747774";
    long start = System.nanoTime();
    ans = s.multiply(a, b);
    long end = System.nanoTime();
    System.out.println("elapsed time (s): " + (double) (end - start) / 1000.0 / 1000.0 / 1000.0);
    System.out.println(a + " * " + b + " = " + ans);
    if (!"600551678379277416295701654391322759435567182334825099268876949038935586332072681055603050825531427341487345211132597254519042068526750992377730361293500451707930720404409116011100636242669003183795409703514233439381210264731883177195591302447992460770894611200967811707866454404386541356452380172833250491297723897598419622945913970406766260708078966971385139986323271885332493679485767628110887453845114291667097643298056443228943518038407423847846268635139689759203719899567465215510885610067994586899976867716516218608139162050527926018706698414728574669035642765610769789303469744382701726529932621026844858130261151436184148593186667498925299791465492681893164590249301832164432775641395092088892832119538382570602820237102070140063883573180128984113895427466520122926799238305388074009657900610517310722225178682217103815379534499827303745960592952878395935097171012978804160832423884224672746938475892095174000448974286868205547845133566598343296993559628924325461966904603653016268920269352754046463705021503787048747485821061079830828546455382060307772685502840792067102046189918536178321350987441032689437673928732351361696776078860677622206375491233477083986478499889448485736681884956655486122967314287998431978980409026337310208161981285023826912374865035574429459890936349775249149639765066993851473593516672025522559029391676020200844707187649289576337317216714688761070342111389799599275628762792606847750651870776385574355214087162822348409039835072411709432671737334108345128957820343250524965054436626474664179888592547223768354862421183517089475123533013441346407677102227076774279820801946872632331530575880898782059152921109800742244573615261970359995643204083309312737367815492415673346291568033618673369641598471573667829921797146634791580943435345471133541006063108879487498201213138602313343022468428539127116222696623774608524571615653953263233412720583329279628273152527213780131561443757142076506307619424565014768592311553737555845738376227593187547773956359334739258743853746173381834077134154205898690233567029865194040535298220889565299330595855171603419086204259146182183590505787985474446744361691481576039631781613356001216114298144167164376560796369372521708101831211235144888443585668510959703433870187115697638501430701540403151549951415483065819286725740224839675805060032608836152076723922528722150532665365772347535162131443351412970844465958368126395254473388296459419876443597013397093303317508532905946797816451313778591353236595868748739834311971206310307730849345944779807160805659359745107847501187450939085989401602962661443019537269314141685483533955031034041313021625270579367311272237732555934270076227151267855267311924418404762869554555764637636230944373760546431074332982286315369484330229516844718033430262387900436125998512681310575309998179537890847490794077815629781886163987853151103541320139526134370641341002398580585285757023189855272272050907806445303674618174585185343637908015061968307406870496264818797084561204436435498578397186496899436371157366291301514566813721901039586580844756851880569563331338833736804582423418152451579745256268787245432662692687877304378686533785834945379449410918716812302588003069432605331590831323826979075523822448680078915537933332381374487995523695283142646825046380755341884577255426599086589954957144048971718682996306149971840696451054606946414501317913454350681980743611455194800970812387071799427452084431350968105601909345385983112568838583061510853082083840339089979707732232823170511393198949937160448057944672263251173950469650600523827784904580809016979321238853187837578176097063720554298671710638569482288335069949116606528859839260889514684127799188264359225976202960562979934438025107662196164418989714988274736393832679895349769711906607853235998725246620468903459062538039575145742361824838843863307339017381326513753514729872079599045837964850263599193254647933064245285251315109932805658687066772888403497281808366409051472583494028128628561393100448139646100071137212777446950817474973027826583983279919149790755646393200925683389941436782943352793036073804808353535280656491915593119267702508610228186601131841562888415431048600016452354319638329501185163888629956407477092516413709999840978815975611156954393831170037437554893181394308151383078527951691322834279438248346535351090525764065547517685499108331560564187921314059603120074474116892346715729590425591695944977589570088414302887119717758265584286311015659353475602082418878556840646549951050047618819031890162193837224439176643847538175454408029993756954183457833022153446696266484617192283876343870061851312606323959484017308072682852923609452824732030279808503529228202504356835350345899374347731782885343388071058957214574928956430206881212605348177835448547711949069515868284104372591907900125271125642466340303269930145910639143683343851575623000908291086906222160397599752259472705045144007034101855943227249359806801737096680186998258494398629982101118621348566758716862246091996495607606229145078171691798249972494116957347972399118092120649378756487563615367713372263154049908784029339115939246"
        .equals(ans)) {
      throw new AssertionError();
    }
  }
}