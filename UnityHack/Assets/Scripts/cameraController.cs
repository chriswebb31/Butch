using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class cameraController : MonoBehaviour {
    //Cursor variables
    // public Texture2D cursorTexture;
    public float cursorX;
    public float cursorY;
    public float distanceDivisor;

    //camera variables
    public float cameraHeight;
    public float camreaFollowSpeed;
    public GameObject cameraCenterTarget;
    public GameObject player;

	// Use this for initialization
	void Start () {
        cameraCenterTarget = new GameObject();
	}
	
	// Update is called once per frame
	void Update () {
        //Get Cursor Position
        Vector3 cursorPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition); //cursor position in world coords
        cursorX = cursorPosition.x;
        cursorY = cursorPosition.y;

        //move camera target to center of player and cursors midpoint
        cursorX = cursorX + (player.transform.position.x - cursorX) / distanceDivisor; ; //half the x distance
        cursorY = cursorY + (player.transform.position.y - cursorY) / distanceDivisor; //half the y distance
        cameraCenterTarget.transform.position = new Vector2(cursorX, cursorY); //set the midpoints position

        //lerp camera to target
        Vector3 targetLerp = Vector3.Lerp(this.transform.position, cameraCenterTarget.transform.position, camreaFollowSpeed);
        targetLerp.z = cameraHeight;

        this.gameObject.transform.position = targetLerp;
        
    }
}
