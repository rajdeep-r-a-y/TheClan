
# TheClan

TheClan is a native android based social media application where a user can express whatever is in his or her mind in the form of words and emojis. You can accumulate likes on your post. In case you are feeling bored, you can watch memes through the click of a button.
This application is aimed towards making a funny community where people can share jokes and have fun.

## Backend

In this application, a user can log in through his/her Google account and post whatever the person wants. The details of the user - user account and posts are stored in a realtime NoSQL database- Google Firebase. Any changes made to user data (like if you receive a like on your post or a user adds a post) will be reflected in real time. 

The documentation to use Google Firebase in your projects is linked below:
```http
https://developer.android.com/studio/write/firebase
```

  
## API Reference

#### I have used the following API to fetch memes :

```http
   https://meme-api.herokuapp.com/gimme
```

I have used an external library called Volley to make network requests. The link to its documentation is provided below:
```http
   https://developer.android.com/training/volley/request
```

 To load images from the API, i have used Glide library. The Github link is provided below:
 ```http
 https://github.com/bumptech/glide
 ```
## Demo

This is a youtube link of the demo of this application.

  https://youtu.be/TbzJ5-l3Sk4
## Deployment

To deploy this application, simply install the apk of this application on your "Android" phone and log in using your Google account.



  
