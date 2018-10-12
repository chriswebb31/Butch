using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerController : MonoBehaviour {
    public float playerSpeed;
    public float playerSprintSpeed;
    private Rigidbody2D rigidbodyComponent;

    //Inputs
    private float horizontalInput;
    private float verticalInput;


    private void Start() //Runs Once when started
    {
        rigidbodyComponent = this.GetComponent<Rigidbody2D>(); //Rigidbody is the physics component of the player object
        rigidbodyComponent.gravityScale = 0; //As top down there is no gravity
        rigidbodyComponent.isKinematic = true; //We control the movement via scripts not the physics engine, not as slidy movement
    }

    private void Update() //runs every avaliable frame, good for inputs and registering
    {
        inputHandling(); //keep update loops clean pls
        Vector2 movementVector = new Vector2(horizontalInput * playerSpeed, verticalInput * playerSpeed); //player velocity is in this direction and magnitude


        rigidbodyComponent.velocity = movementVector;
    }

    private void FixedUpdate() //runs every fixed frame, cleaner for movement and physics
    {
        
    }

    private void inputHandling()
    {
        horizontalInput = Input.GetAxis("Horizontal"); //input library get axis is the horizontal input axis (defualt arrow keys or a d) at values between -1 and 1
        verticalInput = Input.GetAxis("Vertical");
    }

}
