package com.example.nano1.moviedb;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nano1 on 5/25/2016.
 */
public class Movie implements Parcelable {

    /**
     * page : 2
     * results : [{"poster_path":"/ahUiUaeOE2lvnOy7srxaUJbbvYv.jpg","adult":false,"overview":"Set backstage at three iconic product launches and ending in 1998 with the unveiling of the iMac, Steve Jobs takes us behind the scenes of the digital revolution to paint an intimate portrait of the brilliant man at its epicenter.","release_date":"2015-10-09","genre_ids":[36,18],"id":321697,"original_title":"Steve Jobs","original_language":"en","title":"Steve Jobs","backdrop_path":"/uCjyMmKDFmI918hURXsaSNY8T63.jpg","popularity":5.536675,"vote_count":737,"video":false,"vote_average":6.85},{"poster_path":"/2EUAUIu5lHFlkj5FRryohlH6CRO.jpg","adult":false,"overview":"A group of scientists in San Francisco struggle to stay alive in the aftermath of a plague that is wiping out humanity, while Caesar tries to maintain dominance over his community of intelligent apes.","release_date":"2014-06-26","genre_ids":[878,28,18,53],"id":119450,"original_title":"Dawn of the Planet of the Apes","original_language":"en","title":"Dawn of the Planet of the Apes","backdrop_path":"/rjUl3pd1LHVOVfG4IGcyA1cId5l.jpg","popularity":5.528285,"vote_count":2271,"video":false,"vote_average":7.43},{"poster_path":"/4mFsNQwbD0F237Tx7gAPotd0nbJ.jpg","adult":false,"overview":"A true story of two men who should never have met - a quadriplegic aristocrat who was injured in a paragliding accident and a young man from the projects.","release_date":"2011-11-02","genre_ids":[18,35],"id":77338,"original_title":"Intouchables","original_language":"fr","title":"The Intouchables","backdrop_path":"/ihWaJZCUIon2dXcosjQG2JHJAPN.jpg","popularity":5.471178,"vote_count":2401,"video":false,"vote_average":8.12},{"poster_path":"/aqNJrAxudMRNo8jg3HOUQqdl2xr.jpg","adult":false,"overview":"Brooklyn mobster and prolific hit man Jimmy Conlon, once known as The Gravedigger, has seen better days. Longtime best friend of a mob boss, Jimmy, now 55, is haunted by the sins of his past\u2014as well as a dogged police detective who\u2019s been one step behind Jimmy for 30 years. But when Jimmy\u2019s estranged son becomes a target, Jimmy must make a choice between the crime family he chose and the real family he abandoned long ago. Now, with nowhere safe to turn, Jimmy has just one night to figure out exactly where his loyalties lie and to see if he can finally make things right.","release_date":"2015-03-11","genre_ids":[28,80,18,9648,53],"id":241554,"original_title":"Run All Night","original_language":"en","title":"Run All Night","backdrop_path":"/rSf0RYkPiMo9TyjHs2vI8rHlmfF.jpg","popularity":5.395336,"vote_count":672,"video":false,"vote_average":6.27},{"poster_path":"/btbRB7BrD887j5NrvjxceRDmaot.jpg","adult":false,"overview":"Caleb, a 26 year old coder at the world's largest internet company, wins a competition to spend a week at a private mountain retreat belonging to Nathan, the reclusive CEO of the company. But when Caleb arrives at the remote location he finds that he will have to participate in a strange and fascinating experiment in which he must interact with the world's first true artificial intelligence, housed in the body of a beautiful robot girl.","release_date":"2015-01-21","genre_ids":[18,878],"id":264660,"original_title":"Ex Machina","original_language":"en","title":"Ex Machina","backdrop_path":"/9X3cDZb4GYGQeOnZHLwMcCFz2Ro.jpg","popularity":5.368185,"vote_count":2132,"video":false,"vote_average":7.6},{"poster_path":"/p11Ftd4VposrAzthkhF53ifYZRl.jpg","adult":false,"overview":"The men who made millions from a global economic meltdown.","release_date":"2015-12-11","genre_ids":[18,35],"id":318846,"original_title":"The Big Short","original_language":"en","title":"The Big Short","backdrop_path":"/jmlMLYEsYY1kRc5qHIyTdxCeVmZ.jpg","popularity":5.348082,"vote_count":899,"video":false,"vote_average":7.15},{"poster_path":"/rzMYZYphL9qrbBClQq1Jc0zPMmY.jpg","adult":false,"overview":"Tells the comedic tale of Eddie Mannix, a fixer who worked for the Hollywood studios in the 1950s. The story finds him at work when a star mysteriously disappears in the middle of filming.","release_date":"2016-02-05","genre_ids":[35,18,10402,9648],"id":270487,"original_title":"Hail, Caesar!","original_language":"en","title":"Hail, Caesar!","backdrop_path":"/ao5p2zi0DC7hMsZuYvKNlgnTigq.jpg","popularity":5.297197,"vote_count":304,"video":false,"vote_average":5.57},{"poster_path":"/50d0XQQETSyg3bwBXhC7K33pKgc.jpg","adult":false,"overview":"Billy \"The Great\" Hope, the reigning junior middleweight boxing champion, has an impressive career, a loving wife and daughter, and a lavish lifestyle. However, when tragedy strikes, Billy hits rock bottom, losing his family, his house and his manager. He soon finds an unlikely savior in Tick Willis, a former fighter who trains the city's toughest amateur boxers. With his future on the line, Hope fights to reclaim the trust of those he loves the most.","release_date":"2015-06-15","genre_ids":[28,18],"id":307081,"original_title":"Southpaw","original_language":"en","title":"Southpaw","backdrop_path":"/2vFH3ntJbBC7QCwB0nRJGLx0oPU.jpg","popularity":5.212511,"vote_count":1086,"video":false,"vote_average":7.25},{"poster_path":"/jV8wnk3Jgz6f7degmT3lHNGI2tK.jpg","adult":false,"overview":"When college senior Anastasia Steele steps in for her sick roommate to interview prominent businessman Christian Grey for their campus paper, little does she realize the path her life will take. Christian, as enigmatic as he is rich and powerful, finds himself strangely drawn to Ana, and she to him. Though sexually inexperienced, Ana plunges headlong into an affair -- and learns that Christian's true sexual proclivities push the boundaries of pain and pleasure.","release_date":"2015-02-11","genre_ids":[18,10749],"id":216015,"original_title":"Fifty Shades of Grey","original_language":"en","title":"Fifty Shades of Grey","backdrop_path":"/zw3fM9KYYhYGsIQUJOyQNbeZSnn.jpg","popularity":5.014352,"vote_count":1505,"video":false,"vote_average":5.32},{"poster_path":"/p2SdfGmQRaw8xhFbexlHL7srMM8.jpg","adult":false,"overview":"A young female FBI agent joins a secret CIA operation to take down a Mexican cartel boss, a job that ends up pushing her ethical and moral values to the limit.","release_date":"2015-09-17","genre_ids":[28,80,18,9648,53],"id":273481,"original_title":"Sicario","original_language":"en","title":"Sicario","backdrop_path":"/zAHozevwbbsB2epEVpCkzqVccWW.jpg","popularity":4.986827,"vote_count":1073,"video":false,"vote_average":7.03},{"poster_path":"/vdK1f9kpY5QEwrAiXs9R7PlerNC.jpg","adult":false,"overview":"A dramatic thriller based on the incredible true David vs. Goliath story of American immigrant Dr. Bennet Omalu, the brilliant forensic neuropathologist who made the first discovery of CTE, a football-related brain trauma, in a pro player and fought for the truth to be known. Omalu's emotional quest puts him at dangerous odds with one of the most powerful institutions in the world.","release_date":"2015-11-12","genre_ids":[18],"id":321741,"original_title":"Concussion","original_language":"en","title":"Concussion","backdrop_path":"/vu82PVUjEaAmrxBdeH8hbx2ZBxy.jpg","popularity":4.985513,"vote_count":264,"video":false,"vote_average":6.91},{"poster_path":"/7xvfhwi1RjF27z3TfaJFAsIaO3x.jpg","adult":false,"overview":"A thriller set in New York City during the winter of 1981, statistically one of the most violent years in the city's history, and centered on a the lives of an immigrant and his family trying to expand their business and capitalize on opportunities as the rampant violence, decay, and corruption of the day drag them in and threaten to destroy all they have built.","release_date":"2014-12-30","genre_ids":[28,53,80,18],"id":241239,"original_title":"A Most Violent Year","original_language":"en","title":"A Most Violent Year","backdrop_path":"/cPg2OJ56J6p0da12Hez5u5iiFd6.jpg","popularity":4.92622,"vote_count":315,"video":false,"vote_average":6.44},{"poster_path":"/uekIGnzTACciWrfR7jOuof7xHgR.jpg","adult":false,"overview":"In the winter of 1820, the New England whaling ship Essex was assaulted by something no one could believe: a whale of mammoth size and will, and an almost human sense of vengeance.  The real-life maritime disaster would inspire Herman Melville\u2019s Moby Dick.  But that told only half the story.  \u201cHeart of the Sea\u201d reveals the encounter\u2019s harrowing aftermath, as the ship\u2019s surviving crew is pushed to their limits and forced to do the unthinkable to stay alive.  Braving storms, starvation, panic and despair, the men will call into question their deepest beliefs, from the value of their lives to the morality of their trade, as their captain searches for direction on the open sea and his first mate still seeks to bring the great whale down.","release_date":"2015-11-20","genre_ids":[53,18,12,28,36],"id":205775,"original_title":"In the Heart of the Sea","original_language":"en","title":"In the Heart of the Sea","backdrop_path":"/vJfivaGbEvDLJgZGthUUVBLeFB5.jpg","popularity":4.893792,"vote_count":563,"video":false,"vote_average":6.23},{"poster_path":"/xxe77USOk2tPnq7G4cL42gf1OxQ.jpg","adult":false,"overview":"Private investigator Matthew Scudder is hired by a drug kingpin to find out who kidnapped and murdered his wife.","release_date":"2014-09-18","genre_ids":[80,18,9648,53],"id":169917,"original_title":"A Walk Among the Tombstones","original_language":"en","title":"A Walk Among the Tombstones","backdrop_path":"/e56QsaJy1weAUukiK2ZmIGVUALF.jpg","popularity":4.569183,"vote_count":714,"video":false,"vote_average":6.21},{"poster_path":"/2tZqOGvOT5ad6RNc10CYJ2Ol2ED.jpg","adult":false,"overview":"World War II soldier-turned-U.S. marshal Teddy Daniels investigates the disappearance of a patient from a hospital for the criminally insane, but his efforts are compromised by his own troubling visions and by Dr. Cawley.","release_date":"2010-02-18","genre_ids":[18,53,9648],"id":11324,"original_title":"Shutter Island","original_language":"en","title":"Shutter Island","backdrop_path":"/fmLWuAfDPaUa3Vi5nO1YUUyZaX6.jpg","popularity":4.545851,"vote_count":2971,"video":false,"vote_average":7.52},{"poster_path":"/lj8AMCjWx0jU93XajzUEOUUNKZF.jpg","adult":false,"overview":"Bob Saginowski finds himself at the center of a robbery gone awry and entwined in an investigation that digs deep into the neighborhood's past where friends, families, and foes all work together to make a living - no matter the cost.","release_date":"2014-09-12","genre_ids":[18,80],"id":154400,"original_title":"The Drop","original_language":"en","title":"The Drop","backdrop_path":"/712DGw9WAE9iOvBd426s09Mo63m.jpg","popularity":4.48165,"vote_count":513,"video":false,"vote_average":6.54},{"poster_path":"/ozKPo7l668X8yatgg4wIummpmQG.jpg","adult":false,"overview":"Laura is a 19-year-old university freshman who desperately wants to do well in school. She works a part-time job but cannot make ends meet. One evening in which she is short of funds, she answers a personal ad online by \"Joe,\" 57, who seeks a female student for \"tender moments.\" The pay is 100 euros per hour. Laura pledges to do this just once, and three days later, she goes to a hotel room with Joe. And then her spiral begins.","release_date":"2010-01-18","genre_ids":[18],"id":65496,"original_title":"Mes chères études","original_language":"fr","title":"Student Services","backdrop_path":"/5EU3ZEGUSfDzFQpHvjW4vBfGHSy.jpg","popularity":4.445127,"vote_count":18,"video":false,"vote_average":5.31},{"poster_path":"/39AriYTuZbwsMrTMc72mkO8dbVh.jpg","adult":false,"overview":"Bored and restless, Alice spends much of her time lusting after Jim, a local sawmill worker. When not lusting after him, Alice fills the hours with such pursuits as writing her name on a mirror with vaginal secretions and wandering the fields with her underwear around her ankles. And, in true teenaged tradition, she spends a lot of time writing in her diary.","release_date":"1976-01-01","genre_ids":[18],"id":1631,"original_title":"Une vraie jeune fille","original_language":"fr","title":"A Real Young Girl","backdrop_path":"/3qFFHnFkULFJPY3ZRvRD0U2r6b8.jpg","popularity":4.398814,"vote_count":10,"video":false,"vote_average":6.1},{"poster_path":"/f7DImXDebOs148U4uPjI61iDvaK.jpg","adult":false,"overview":"A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.","release_date":"1997-12-20","genre_ids":[35,18],"id":637,"original_title":"La vita è bella","original_language":"it","title":"Life Is Beautiful","backdrop_path":"/bORe0eI72D874TMawOOFvqWS6Xe.jpg","popularity":4.368033,"vote_count":1394,"video":false,"vote_average":8.04},{"poster_path":"/xOdtGQUXlE6V2lIiloV70TqP9xG.jpg","adult":false,"overview":"Lee Gates is a TV personality whose insider tips have made him the money guru of Wall Street. When Kyle loses all of his family's money on a bad tip, he holds Lee and his entire show hostage on air threatening to kill Lee if he does not get the stock up 24 and a half points before the bell.","release_date":"2016-05-12","genre_ids":[18,53],"id":303858,"original_title":"Money Monster","original_language":"en","title":"Money Monster","backdrop_path":"/o6YRHJoUg5FaV88zdEPoi8zg3JV.jpg","popularity":4.331875,"vote_count":52,"video":false,"vote_average":5.63}]
     * total_results : 21184
     * total_pages : 1060
     */

    private int page;
    private int total_results;
    private int total_pages;
    /**
     * poster_path : /ahUiUaeOE2lvnOy7srxaUJbbvYv.jpg
     * adult : false
     * overview : Set backstage at three iconic product launches and ending in 1998 with the unveiling of the iMac, Steve Jobs takes us behind the scenes of the digital revolution to paint an intimate portrait of the brilliant man at its epicenter.
     * release_date : 2015-10-09
     * genre_ids : [36,18]
     * id : 321697
     * original_title : Steve Jobs
     * original_language : en
     * title : Steve Jobs
     * backdrop_path : /uCjyMmKDFmI918hURXsaSNY8T63.jpg
     * popularity : 5.536675
     * vote_count : 737
     * video : false
     * vote_average : 6.85
     */

    private List<ResultsEntity> results;

    protected Movie(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_results);
        dest.writeInt(total_pages);
    }

    public static class ResultsEntity implements Parcelable {
        private String poster_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private int id;
        private String original_title;
        private String original_language;
        private String title;
        private String backdrop_path;
        private double popularity;
        private int vote_count;
        private boolean video;
        private double vote_average;
        private List<Integer> genre_ids;

        protected ResultsEntity(Parcel in) {
            poster_path = in.readString();
            adult = in.readByte() != 0;
            overview = in.readString();
            release_date = in.readString();
            id = in.readInt();
            original_title = in.readString();
            original_language = in.readString();
            title = in.readString();
            backdrop_path = in.readString();
            popularity = in.readDouble();
            vote_count = in.readInt();
            video = in.readByte() != 0;
            vote_average = in.readDouble();
        }

        public static final Creator<ResultsEntity> CREATOR = new Creator<ResultsEntity>() {
            @Override
            public ResultsEntity createFromParcel(Parcel in) {
                return new ResultsEntity(in);
            }

            @Override
            public ResultsEntity[] newArray(int size) {
                return new ResultsEntity[size];
            }
        };

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(poster_path);
            dest.writeByte((byte) (adult ? 1 : 0));
            dest.writeString(overview);
            dest.writeString(release_date);
            dest.writeInt(id);
            dest.writeString(original_title);
            dest.writeString(original_language);
            dest.writeString(title);
            dest.writeString(backdrop_path);
            dest.writeDouble(popularity);
            dest.writeInt(vote_count);
            dest.writeByte((byte) (video ? 1 : 0));
            dest.writeDouble(vote_average);
        }
    }
}
